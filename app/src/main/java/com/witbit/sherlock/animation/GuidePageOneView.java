package com.witbit.sherlock.animation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.witbit.sherlock.oneapp.R;

/**
 * Created by sherlock on 17/9/14.
 */

public class GuidePageOneView extends BaseGudiePageView {
	private Context mContext;

	public GuidePageOneView(Context context) {
		super(context);
	}

	@Override
	protected void initView() {
		LayoutInflater.from(mContext).inflate(R.layout.item_guide_page_1, this);


	}
}
