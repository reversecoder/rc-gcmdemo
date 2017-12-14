package com.reversecoder.gcm.demo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class GcmUtils {

    public static void registerPushReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter(GcmConstants.BROADCAST_NOTIFICATION);
        intentFilter.setPriority(1);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
