package com.reversecoder.gcm.demo.service;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class GcmService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Intent intent = new Intent(this, PushReceiverIntentService.class);
        intent.putExtras(data);
        startService(intent);
    }
}
