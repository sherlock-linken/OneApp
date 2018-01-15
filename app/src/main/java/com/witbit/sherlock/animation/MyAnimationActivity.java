package com.witbit.sherlock.animation;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.witbit.sherlock.oneapp.R;

public class MyAnimationActivity extends AppCompatActivity {

	private ImageView test_img;

	private ViewPager vp_guide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_animation);

		vp_guide = (ViewPager) findViewById(R.id.vp_guide);




	}

	private class GuidePagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return false;
		}
	}
}
