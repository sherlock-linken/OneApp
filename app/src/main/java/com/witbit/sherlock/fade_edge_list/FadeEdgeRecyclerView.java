package com.witbit.sherlock.fade_edge_list;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class FadeEdgeRecyclerView extends RecyclerView {

    public FadeEdgeRecyclerView(Context context) {
        super(context);
        init();
    }

    public FadeEdgeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FadeEdgeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHorizontalFadingEdgeEnabled(true);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected float getLeftFadingEdgeStrength() {
        return canScrollHorizontally(-1) ? 1f : 0f;
    }

    @Override
    protected float getRightFadingEdgeStrength() {
        return canScrollHorizontally(1) ? 1f : 0f;
    }
}
