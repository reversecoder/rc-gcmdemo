package com.reversecoder.gcm.demo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * Created by rashed on 12/13/17.
 */

public class GcmUtils {

    public void registerPushReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter(GcmConstants.BROADCAST_NOTIFICATION);
        intentFilter.setPriority(1);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
