package com.example.customview;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class NotifitycationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		TextView tv = new TextView(this);
		tv.setText("it's a notification");
		setContentView(tv);
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(1);
	}
}
