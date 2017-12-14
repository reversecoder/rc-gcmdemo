package com.reversecoder.gcm.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.reversecoder.gcm.demo.R;
import com.reversecoder.gcm.demo.model.Notification;
import com.reversecoder.gcm.demo.service.PushReceiverIntentService;

import static com.reversecoder.gcm.demo.util.GcmConstants.INTENT_KEY_NOTIFICATION;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dont_abort_broadcast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReceivePushAndDontAbortActivity.class));
            }
        });

        findViewById(R.id.abort_broadcast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReceivePushAndAbortActivity.class));
            }
        });

        findViewById(R.id.emit_and_close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchNotificationAndFinish();
            }
        });
    }

    private void dispatchNotificationAndFinish() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, PushReceiverIntentService.class);
                Notification notification = new Notification("Default Notification", "This notification was received in default Receiver");
                intent.putExtra(INTENT_KEY_NOTIFICATION, notification);
                startService(intent);
            }
        }, 2000);

        finish();
    }
}
