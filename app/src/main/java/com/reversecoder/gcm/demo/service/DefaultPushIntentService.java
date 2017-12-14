package com.reversecoder.gcm.demo.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.reversecoder.gcm.demo.R;
import com.reversecoder.gcm.demo.model.Notification;

import java.util.concurrent.atomic.AtomicInteger;

import static com.reversecoder.gcm.demo.util.GcmConstants.INTENT_KEY_NOTIFICATION;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class DefaultPushIntentService extends IntentService {

    AtomicInteger uuid = new AtomicInteger(1);

    public DefaultPushIntentService() {
        super("DefaultPushIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        handleNotification(intent.getExtras());
    }

    private void handleNotification(Bundle extras) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = extras.getParcelable(INTENT_KEY_NOTIFICATION);

        if (notification == null) {
            return;
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getMessage())
                .setAutoCancel(true);

        mNotificationManager.notify("default-push", 1, mBuilder.build());
    }
}
