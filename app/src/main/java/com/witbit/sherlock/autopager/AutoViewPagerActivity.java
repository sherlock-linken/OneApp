package com.witbit.sherlock.autopager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;
import com.witbit.sherlock.oneapp.R;

import java.util.ArrayList;

public class AutoViewPagerActivity extends Activity {

	private ViewPager viewPager;
	private Context mContext;
	private CirclePageIndicator indicator;

	private ArrayList<Integer> dataList = new ArrayList<>();
	private MyPagerAdapter adapter;
	private ArrayList<ImageView> imageViews = new ArrayList<>();

	private int count = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_view_pager);

		mContext = this;

		//		dataList.add(R.drawable.logo);
		//		dataList.add(R.drawable.logo1);
		//		dataList.add(R.drawable.logo2);
		//		dataList.add(R.drawable.logo3);
		//		dataList.add(R.drawable.logo);
		//		dataList.add(R.drawable.logo1);


		dataList.add(R.drawable.a001);
		dataList.add(R.drawable.a002);
		dataList.add(R.drawable.a003);
		dataList.add(R.drawable.a004);
		dataList.add(R.drawable.a005);

		for (int i = 0; i < dataList.size(); i++) {
			ImageView tmp = new ImageView(mContext);
			tmp.setImageResource(dataList.get(i));
			tmp.setScaleType(ImageView.ScaleType.FIT_XY);
			imageViews.add(tmp);
		}

		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		adapter = new MyPagerAdapter();

		viewPager.setAdapter(adapter);
		indicator.setViewPager(viewPager);


		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.sendEmptyMessage(100);
			}
		});
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 100: {

					Log.i("sherlock", "count == " + count);

					viewPager.setCurrentItem(count, true);

					count = (count + 1) % imageViews.size();

					sendEmptyMessageDelayed(100, 1000);
				}
				break;
			}

		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeMessages(100);
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			((ViewPager) container).addView(imageViews.get(position));

			return imageViews.get(position);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (position < imageViews.size())
				((ViewPager) container).removeView(imageViews.get(position));
		}
	}
}
