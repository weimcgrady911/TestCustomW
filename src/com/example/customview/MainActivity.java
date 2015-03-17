package com.example.customview;

import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customview.MyService.MyBinder;

public class MainActivity extends Activity {
	
	String provider ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Log.i("weiqun12345", "current ThreadId = " + Thread.currentThread());
		service = new Intent();
		service.setClass(this, MyService.class);
		
		service2 = new Intent();
		service2.setClass(this, MyIntentService.class);
		Button bnt1 = (Button) findViewById(R.id.button1);
		Button bnt2 = (Button) findViewById(R.id.button2);
		Button bnt3 = (Button) findViewById(R.id.button3);
		bnt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				bindService(service, conn, BIND_AUTO_CREATE);
				startService(service2);
			}
		});
		bnt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mBinder.print("aaaaaaaa");
			}
		});
		bnt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				unbindService(conn);
				stopService(service2);
			}
		});
		
		
		tv_latitude = (TextView) findViewById(R.id.tv_latitude);
		tv_longitude = (TextView) findViewById(R.id.tv_longitude);
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				showLocation(location);
				
			}
		};
		
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = lm.getProviders(true);
		if(providers.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if(providers.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			Toast.makeText(this, "not found locationProvider", Toast.LENGTH_LONG).show();
			return;
		}
		lm.requestLocationUpdates(provider, 5000, 1, locationListener);
		Location location = lm.getLastKnownLocation(provider);
		if(location!=null) {
			showLocation(location);
		}
		
	}

	
	
	public void showLocation(Location location) {
		tv_latitude.setText(location.getLatitude()+"");
		tv_longitude.setText(location.getLongitude()+"");
	}
	
	private MyBinder mBinder;

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mBinder = (MyService.MyBinder) service;
		}
	};

	private Intent service;

	private Intent service2;

	private TextView tv_latitude;

	private TextView tv_longitude;

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void sendNotification() {
		Intent intents = new Intent();
		intents.setClass(this, NotifitycationActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				intents, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				"This is ticker text", System.currentTimeMillis());
		notification.setLatestEventInfo(this, "This is content title",
				"this is content text", contentIntent);
		nm.notify(1, notification);

	}

	class NetWorkChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				Toast.makeText(context, "newtwork is available",
						Toast.LENGTH_LONG).show();
				;
			} else {
				Toast.makeText(context, "newtwork is unavailable",
						Toast.LENGTH_LONG).show();
				;
			}
		}
	}

	class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			String address = messages[0].getOriginatingAddress();
			Log.i("weiqun12345", "address=" + address);
			String fullMessage = "";
			for (SmsMessage message : messages) {
				fullMessage = message.getMessageBody();
			}
			Log.i("weiqun12345", "fullMessage=" + fullMessage);
			abortBroadcast();
		}
	}
}
