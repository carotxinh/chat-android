package vn.unima.demoapps.chat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;

import vn.unima.demoapps.chat.R;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class AppUtils {

    public static final String ARG_ROOMS = "rooms";

    public static void showMessage(Context context, String message, DialogInterface.OnClickListener onClickListener, DialogInterface.OnDismissListener onDismissListener) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .setOnDismissListener(onDismissListener)
                .show();
    }

    public static void showMessage(Context context, @StringRes int message, DialogInterface.OnClickListener onClickListener, DialogInterface.OnDismissListener onDismissListener) {
        showMessage(context, context.getString(message), onClickListener, onDismissListener);
    }

    public static void navigateUp(@NonNull Activity activity) {
        Intent upIntent = NavUtils.getParentActivityIntent(activity);
        if (upIntent == null) {
            activity.finish();
        } else if (NavUtils.shouldUpRecreateTask(activity, upIntent)) {
            // This activity is NOT part of this app's task, so create a new task
            // when navigating up, with a synthesized back stack.
            TaskStackBuilder.create(activity)
                    // Add all of this activity's parents to the back stack
                    .addNextIntentWithParentStack(upIntent)
                    // Navigate up to the closest parent
                    .startActivities();
        } else {
            // This activity is part of this app's task, so simply
            // navigate up to the logical parent activity.
            NavUtils.navigateUpTo(activity, upIntent);
        }
    }
}
