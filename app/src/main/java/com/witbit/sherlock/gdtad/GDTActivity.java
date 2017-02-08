package com.witbit.sherlock.gdtad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.witbit.sherlock.oneapp.R;

import java.util.List;

public class GDTActivity extends AppCompatActivity {

	private NativeAD gdtADLoader;
	private com.qq.e.ads.nativ.NativeADDataRef gdtAD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gdt);

		if (gdtADLoader == null) {
			gdtADLoader = new NativeAD(this, "1105705311", "6080216612558614", gdtADLoadListener);
			gdtADLoader.setBrowserType(BrowserType.Sys);
			gdtADLoader.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.Default);
		}


		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gdtADLoader.loadAD(1);
			}
		});
	}

	private NativeAD.NativeAdListener gdtADLoadListener = new NativeAD.NativeAdListener() {
		@Override
		public void onADLoaded(List<NativeADDataRef> list) {
			if (list != null && list.size() > 0) {
				gdtAD = list.get(0);
				Log.i("sherlock", "GDTActivity.onADLoaded == " + gdtAD.getTitle());
			}
		}

		@Override
		public void onNoAD(int i) {
			Log.i("sherlock", "NewGameFragment.onNoAD i == " + i);
		}

		@Override
		public void onADStatusChanged(com.qq.e.ads.nativ.NativeADDataRef nativeADDataRef) {
			Log.i("sherlock", "NewGameFragment.onADStatusChanged called");
		}

		@Override
		public void onADError(com.qq.e.ads.nativ.NativeADDataRef nativeADDataRef, int i) {
			Log.i("sherlock", "NewGameFragment.onADError i == " + i);
		}
	};
}
