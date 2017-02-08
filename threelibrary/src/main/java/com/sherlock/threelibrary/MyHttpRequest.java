package com.sherlock.threelibrary;

import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sherlock on 16/7/26.
 */
public class MyHttpRequest {

	public static void getHttp() {
		final String path = "http://www.baidu.com";
		//访问网络，把html源文件下载下来
		new Thread() {
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");//声明请求方式 默认get
					//conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3.3; zh-cn; sdk
					// Build/GRI34) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1
					// MicroMessenger/6.0.0.57_r870003.501 NetType/internet");
					int code = conn.getResponseCode();
					if (code == 200) {
						InputStream is = conn.getInputStream();
						String result = readStream(is);

						Log.i("sherlock", "MyHttpRequest.run result == " + result);

						//						Message msg = Message.obtain();//减少消息创建的数量
						//						msg.obj = result;
						//						msg.what = SUCCESS;
						//						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					//					Message msg = Message.obtain();//减少消息创建的数量
					//					msg.what = ERROR;
					//					handler.sendMessage(msg);
					e.printStackTrace();
				}
			}

			;
		}.start();
	}

	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			is.close();
			String temptext = new String(baos.toByteArray());
			if (temptext.contains("charset=gb2312")) {//解析meta标签
				return new String(baos.toByteArray(), "gb2312");
			}
			else {
				return new String(baos.toByteArray(), "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
