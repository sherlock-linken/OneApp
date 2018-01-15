package com.witbit.sherlock.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.witbit.sherlock.oneapp.R;

/**
 * Created by sherlock on 17/10/14.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View> {

	private int targetId;
	int offsetTotal = 0;
	boolean scrolling = false;

	public MyBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Follow);
		for (int i = 0; i < a.getIndexCount(); i++) {
			int attr = a.getIndex(i);
			if(a.getIndex(i) == R.styleable.Follow_target){
				targetId = a.getResourceId(attr, -1);
			}
		}
		a.recycle();

	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
		return true;
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		offset(child, dyConsumed);
	}

	public void offset(View child,int dy){
		int old = offsetTotal;
		int top = offsetTotal - dy;
		top = Math.max(top, -child.getHeight());
		top = Math.min(top, 0);
		offsetTotal = top;
		if (old == offsetTotal){
			scrolling = false;
			return;
		}
		int delta = offsetTotal-old;
		child.offsetTopAndBottom(delta);
		scrolling = true;
	}

	static final Class<?>[] CONSTRUCTOR_PARAMS = new Class<?>[] {
			Context.class,
			AttributeSet.class
	};

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
		return dependency.getId() == targetId;

	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
		Log.i("sherlock", "MyBehavior.onDependentViewChanged called");
		child.setY(dependency.getY()-child.getHeight());
		return true;
	}
}
