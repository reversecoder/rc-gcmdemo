package com.reversecoder.gcm.demo.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class DefaultPushReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mIntent = new Intent(context, DefaultPushIntentService.class);
        mIntent.putExtras(getResultExtras(true));
        context.startService(mIntent);
    }
}
