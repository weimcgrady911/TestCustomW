package com.example.customview;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("weiqun12345", "current ThreadId = " + Thread.currentThread());
	}

}
