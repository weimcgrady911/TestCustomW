package com.example.customview;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private MyBinder mBinder = new MyBinder();
	
	class MyBinder extends Binder {
		public void print(String msg) {
			printA(msg);
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public void printA(String msg) {
		Log.i("weiqun12345", "printA=" + msg);
	}

	@Override
	public void onCreate() {
		Log.i("weiqun12345","onCreate");
		super.onCreate();
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); 
		Notification notification = new Notification(R.drawable.ic_launcher,"service title",System.currentTimeMillis());
		notification.setLatestEventInfo(this, "content title", "content text", pendingIntent);
		startForeground(1, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("weiqun12345","onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.i("weiqun12345","onDestroy");
		super.onDestroy();
	}
	
	
}
