package com.witbit.sherlock.downloadinstall;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sherlock on 16/10/14.
 */

public class AutoInstallAPKDownloadReceiver extends BroadcastReceiver {

	public static ArrayList<Long> ids = new ArrayList<>();

	@Override
	public void onReceive(Context context, Intent intent) {
		long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
		if (ids.contains(myDwonloadID)) {
			ids.remove(myDwonloadID);
			String serviceString = Context.DOWNLOAD_SERVICE;
			DownloadManager dManager = (DownloadManager) context.getSystemService(serviceString);
			Intent install = new Intent(Intent.ACTION_VIEW);
			Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);
			Log.i("sherlock", "AutoInstallAPKDownloadReceiver.onReceive downloadFileUri.getPath == "+downloadFileUri
					.getPath());
			install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
			install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(install);

//			File apkFile = queryDownloadedApk(context,myDwonloadID);
//			Intent aa = new Intent(Intent.ACTION_VIEW);
//			aa.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			aa.setDataAndType(Uri.fromFile(apkFile),"application/vnd.android.package-archive");
//			context.startActivity(aa);
		}
	}

	//通过downLoadId查询下载的apk，解决6.0以后安装的问题
	public static File queryDownloadedApk(Context context,long downloadId) {
		File targetApkFile = null;
		DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		if (downloadId != -1) {
			DownloadManager.Query query = new DownloadManager.Query();
			query.setFilterById(downloadId);
			query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
			Cursor cur = downloader.query(query);
			if (cur != null) {
				if (cur.moveToFirst()) {
					String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
					if (!TextUtils.isEmpty(uriString)) {
						targetApkFile = new File(Uri.parse(uriString).getPath());
					}
				}
				cur.close();
			}
		}
		return targetApkFile;
	}
}
