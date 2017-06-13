package vn.unima.demoapps.chat.objects;

/**
 * FirebaseChat
 * Created by Michael on 6/8/17.
 * Copyright 2017 Nikmesoft Company, Ltd. All rights reserved.
 */
public class ChatMessage {
    private String uid;
    private String message;
    private String userName;
    private long timeStamp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String senderName) {
        this.userName = senderName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ChatMessage() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatMessage) {
            ChatMessage message = (ChatMessage) obj;
            return message.getUid().equals(uid);
        }
        return false;
    }
}
