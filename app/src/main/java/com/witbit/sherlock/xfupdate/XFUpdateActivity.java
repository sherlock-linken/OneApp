package com.witbit.sherlock.xfupdate;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.iflytek.autoupdate.UpdateType;
import com.witbit.sherlock.oneapp.MainActivity;
import com.witbit.sherlock.oneapp.R;

public class XFUpdateActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xfupdate);

		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;

			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(),
					PackageManager.GET_META_DATA);
			String value = appInfo.metaData.getString("IFLYTEK_CHANNEL");

			((TextView) findViewById(R.id.my_version)).setText(version+" "+value);
		} catch (Exception e) {
			e.printStackTrace();
			((TextView) findViewById(R.id.my_version)).setText("get version error");
		}

		findViewById(R.id.check_new).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//初始化自动更新对象
				final IFlytekUpdate updManager = IFlytekUpdate.getInstance(XFUpdateActivity.this); //开启调试模式,默认不开启
				updManager.setDebugMode(true);

				//开启wifi环境下检测更新,仅对自动更新有效,强制更新则生效
				 updManager.setParameter(UpdateConstants.EXTRA_WIFIONLY, "true");

				// 设置通知栏使用应用icon,详情请见示例
				 updManager.setParameter(UpdateConstants.EXTRA_NOTI_ICON, "true");

				// 设置更新提示类型,默认为通知栏提示
				 updManager.setParameter(UpdateConstants.EXTRA_STYLE,UpdateConstants.UPDATE_UI_DIALOG);

				//自动更新回调方法,详情参考demo
				IFlytekUpdateListener updateListener = new IFlytekUpdateListener() {
					@Override
					public void onResult(int errorcode, UpdateInfo result) {
						if(errorcode == UpdateErrorCode.OK && result!= null) {
							if(result.getUpdateType() == UpdateType.NoNeed) {
								Log.i("sherlock", "XFUpdateActivity.onResult 已经是最新版本！");
								return;
							}
							updManager.showUpdateInfo(XFUpdateActivity.this, result);
						}
						else
						{
							Log.i("sherlock", "XFUpdateActivity.onResult 请求更新失败！\n更新错误码：" + errorcode);
						}
					}
				};
				// 启动自动更新
				updManager.autoUpdate(XFUpdateActivity.this, null);

			}
		});
	}

}
