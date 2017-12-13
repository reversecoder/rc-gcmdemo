package com.reversecoder.gcm.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rashed on 12/13/17.
 */

public class Notification implements Parcelable {

    private String title = "";
    private String message = "";

    public Notification(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    /**************************
     * Methods for parcelable *
     **************************/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(message);
    }

    // Creator
    public static final Creator CREATOR = new Creator() {
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    // "De-parcel object
    public Notification(Parcel in) {
        this.title = in.readString();
        this.message = in.readString();
    }
}
