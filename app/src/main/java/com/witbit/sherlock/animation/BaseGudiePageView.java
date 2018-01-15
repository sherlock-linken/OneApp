package com.witbit.sherlock.animation;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by sherlock on 17/9/14.
 */

public abstract class BaseGudiePageView extends RelativeLayout {
	public BaseGudiePageView(Context context) {
		super(context);
	}

	protected abstract void initView();
}
