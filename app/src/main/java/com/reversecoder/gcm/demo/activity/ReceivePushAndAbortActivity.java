package com.reversecoder.gcm.demo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.reversecoder.gcm.demo.R;
import com.reversecoder.gcm.demo.model.Notification;
import com.reversecoder.gcm.demo.service.PushReceiverIntentService;

import static com.reversecoder.gcm.demo.util.GcmConstants.INTENT_KEY_NOTIFICATION;
import static com.reversecoder.gcm.demo.util.GcmUtils.registerPushReceiver;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class ReceivePushAndAbortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_push_and_abort);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dispatchNotification();
            }
        }, 1000);
    }

    private void dispatchNotification() {
        Intent intent = new Intent(ReceivePushAndAbortActivity.this, PushReceiverIntentService.class);
        Notification notification = new Notification("ReceivePushAndAbortActivity Notification", "This notification was received in ReceivePushAndAbortActivity");
        intent.putExtra(INTENT_KEY_NOTIFICATION, notification);
        startService(intent);
    }

    private void updateView(Notification notification) {
        ((TextView) findViewById(R.id.notification_title)).setText(notification.getTitle());
        ((TextView) findViewById(R.id.notification_message)).setText(notification.getMessage());
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, getString(R.string.message_received), Toast.LENGTH_SHORT).show();

            Notification notification = intent.getParcelableExtra(INTENT_KEY_NOTIFICATION);
            if (notification != null) {
                updateView(notification);
            }
            abortBroadcast();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        registerPushReceiver(ReceivePushAndAbortActivity.this, receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
