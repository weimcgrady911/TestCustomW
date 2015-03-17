package com.example.customview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isAvailable()) {
			Toast.makeText(context, "newtwork is available", Toast.LENGTH_LONG).show();;
		} else {
			Toast.makeText(context, "newtwork is unavailable", Toast.LENGTH_LONG).show();;
		}
	}

}
