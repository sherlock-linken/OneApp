package com.witbit.sherlock.screenadjust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.witbit.sherlock.oneapp.R;

import java.lang.reflect.Field;

public class ScreenAdjustActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_adjust);

		findViewById(R.id.get_size).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSize();
				int defHeight = getStatusBarHeight();
				Log.i("sherlock", "ScreenAdjustActivity.onClick "+defHeight);
			}
		});


	}

	public void getSize() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		if (dm != null) {
//			((TextView) findViewById(R.id.text_size)).setText("宽：高 == " + dm.widthPixels + " : " + dm.heightPixels);
			((TextView) findViewById(R.id.text_size)).setText(getResources().getDimension(R.dimen.y1280)+" "+ dm.widthPixels + " : " + dm.heightPixels);
		}
	}

	protected int getStatusBarHeight(){
		Log.i("sherlock", "ScreenAdjustActivity.getStatusBarHeight called");
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 38;//默认为38，貌似大部分是这样的
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			Log.i("sherlock", "ScreenAdjustActivity.getStatusBarHeight error called");
			e1.printStackTrace();
		}
		return sbar;
	}
}
