package com.reversecoder.gcm.demo.service;


import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.reversecoder.gcm.demo.model.Notification;

import static com.reversecoder.gcm.demo.util.GcmConstants.BROADCAST_NOTIFICATION;
import static com.reversecoder.gcm.demo.util.GcmConstants.INTENT_KEY_NOTIFICATION;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class PushReceiverIntentService extends IntentService {

//    private Handler.Callback callback = msg -> {
//        throw new IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!");
//    };


    public PushReceiverIntentService() {
        super("PushReceiverIntentService");
    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    };

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Notification notification = extras.getParcelable(INTENT_KEY_NOTIFICATION);

        if (notification != null) {
            sendNotification(notification);
        } else {

            String message = extras.getString("message");
            String url = extras.getString("url");
            Notification mNotification = new Notification(url, message);

            sendNotification(mNotification, extras);
        }
    }

    private void sendNotification(Notification notification) {
        sendNotification(notification, new Bundle());
    }

    private void sendNotification(Notification notification, Bundle extras) {
        Intent broadcast = new Intent();
        extras.putParcelable(INTENT_KEY_NOTIFICATION, notification);
        broadcast.putExtras(extras);
        broadcast.setAction(BROADCAST_NOTIFICATION);

        sendOrderedBroadcast(broadcast, null, null, new Handler(callback), Activity.RESULT_OK, null, extras);
    }

}
