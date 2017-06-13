package vn.unima.demoapps.chat.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import vn.unima.demoapps.chat.R;
import vn.unima.demoapps.chat.abstracts.NMFragment;
import vn.unima.demoapps.chat.utils.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class SignUpFragment extends NMFragment {
    private FirebaseAuth mAuth;

    private EditText edUserName;
    private EditText edMail;
    private EditText edPassword;
    private ProgressDialog loadingDialog;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        loadingDialog = new ProgressDialog(getContext());
        loadingDialog.setMessage(getContext().getString(R.string.message_please_wait));
        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        edUserName = (EditText) view.findViewById(R.id.et_user_name);
        edMail = (EditText) view.findViewById(R.id.et_email);
        edPassword = (EditText) view.findViewById(R.id.et_password);

        view.findViewById(R.id.bt_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    signUp();
                }
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
    }

    private boolean validate() {
        if (TextUtils.isEmpty(edUserName.getText().toString()) || TextUtils.isEmpty(edMail.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
            AppUtils.showMessage(getContext(), R.string.message_field_invalid, null, null);
            return false;
        }
        return true;
    }

    private void signUp() {
        final String mail = edMail.getText().toString();
        final String password = edPassword.getText().toString();

        loadingDialog.show();
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(edUserName.getText().toString())
                                .build();

                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            firebaseUser.updateProfile(profileUpdates);
                        }
                        showMessage();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AppUtils.showMessage(getContext(), e.getMessage(), null, null);
                    }
                })
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadingDialog.dismiss();
                    }
                });
    }

    private void showMessage() {
        AppUtils.showMessage(getContext(), R.string.message_sign_up_success,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                },
                new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        getActivity().finish();
                    }
                });
    }
}
