package com.example.xujichang.nestedscrollinglearn;

import android.support.v4.view.NestedScrollingParentHelper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xujichang on 2016/7/22.
 */
public class CustomNestedScrollingParentHelper extends NestedScrollingParentHelper {
    /**
     * Construct a new helper for a given ViewGroup
     *
     * @param viewGroup
     */
    public CustomNestedScrollingParentHelper(ViewGroup viewGroup) {
        super(viewGroup);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
    }
}
