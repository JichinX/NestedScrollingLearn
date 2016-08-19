package com.example.xujichang.nestedscrollinglearn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by xujichang on 2016/7/21.
 */
public class NestedListView extends ListView implements NestedScrollingChild, NestedScrollingParent {
    private String TAG = "NESTED_SCROLLING";

    private CustomNestedScrollingChildHelper childHelper;
    private CustomNestedScrollingParentHelper parentHelper;

    public NestedListView(Context context) {
        this(context, null);
    }

    public NestedListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        parentHelper = new CustomNestedScrollingParentHelper(this);
        childHelper = new CustomNestedScrollingChildHelper(this);
    }

    /**
     * 子类的方法=====================================================================================
     * setNestedScrollingEnabled
     * isNestedScrollingEnabled
     * startNestedScroll
     * stopNestedScroll
     * hasNestedScrollingParent
     * dispatchNestedScroll
     * dispatchNestedPreScroll
     * dispatchNestedFling
     * dispatchNestedPreFling
     *
     * @param enabled
     */
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        Log.v(TAG, "in      setNestedScrollingEnabled");
        childHelper.setNestedScrollingEnabled(enabled);

    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.v(TAG, "in      startNestedScroll");
        return childHelper.startNestedScroll(axes);

    }

    @Override
    public void stopNestedScroll() {
        Log.v(TAG, "in      stopNestedScroll");
        childHelper.stopNestedScroll();

    }

    @Override
    public boolean hasNestedScrollingParent() {
        Log.v(TAG, "in      hasNestedScrollingParent");

        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.v(TAG, "in      isNestedScrollingEnabled");

        return childHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.v(TAG, "in      dispatchNestedFling");

        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.v(TAG, "in      dispatchNestedPreFling");

        return childHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.v(TAG, "in      dispatchNestedPreScroll");

        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.v(TAG, "in      dispatchNestedScroll");
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPrePerformAccessibilityAction(int action, Bundle arguments) {
        Log.v(TAG, "in      dispatchNestedPrePerformAccessibilityAction");
        return super.dispatchNestedPrePerformAccessibilityAction(action, arguments);
    }

    /**
     * 父类方法======================================================================================
     * onStartNestedScroll
     * onNestedScrollAccepted
     * onStopNestedScroll
     * onNestedScroll
     * onNestedPreScroll
     * onNestedFling
     * onNestedPreFling
     * getNestedScrollAxes
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        return super.onNestedPrePerformAccessibilityAction(target, action, args);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();
    }
}
