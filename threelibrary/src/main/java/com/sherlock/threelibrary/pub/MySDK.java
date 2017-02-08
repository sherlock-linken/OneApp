package com.sherlock.threelibrary.pub;

import android.util.Log;

import com.sherlock.threelibrary.MyHttpRequest;

/**
 * Created by sherlock on 16/7/26.
 */
public class MySDK {

	public static void testLog() {
		Log.i("sherlock", "MySDK.testLog this is log");
	}

	public static int getSum(int a, int b) {
		return a + b;
	}

	public static void testGetHttp() {
		MyHttpRequest.getHttp();
	}

}
