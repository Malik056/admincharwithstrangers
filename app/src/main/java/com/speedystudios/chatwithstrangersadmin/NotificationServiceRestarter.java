package com.speedystudios.chatwithstrangersadmin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by Taha Malik on 23/06/2019.
 **/
public class NotificationServiceRestarter extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(NotificationServiceRestarter.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			context.startForegroundService(new Intent(context, NotificationService.class));
		}
		else{
			context.startService(new Intent(context, NotificationService.class));
		}
	}
}
