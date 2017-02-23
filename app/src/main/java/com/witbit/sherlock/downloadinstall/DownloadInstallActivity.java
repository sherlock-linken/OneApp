package com.witbit.sherlock.downloadinstall;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.witbit.sherlock.oneapp.R;

public class DownloadInstallActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_install);

		findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				downloadAutoInstall(DownloadInstallActivity.this,"http://update.lieyou.com/apps/com.cd.minecraft" +
						".mclauncher/android/version/mconline.apk","myTest");
			}
		});
	}

	/**
	 * 下载并自动安装应用
	 */
	public static void downloadAutoInstall(final Context mContext, final String url, String name) {
		if (TextUtils.isEmpty(name))
			name = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));

		DownloadManager dm = (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		request.setMimeType("application/vnd.android.package-archive");
		request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + ".apk");
		long id = dm.enqueue(request);
		AutoInstallAPKDownloadReceiver.ids.add(id);
		Toast.makeText(mContext, "start download", Toast.LENGTH_SHORT).show();

	}
}
