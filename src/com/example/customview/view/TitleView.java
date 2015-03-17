package com.example.customview.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.customview.R;

public class TitleView extends LinearLayout {

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.custom_view, this);
		Button btn1 = (Button) findViewById(R.id.btn1);
		Button btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "edit", Toast.LENGTH_LONG);
			}
		});
	}
}
