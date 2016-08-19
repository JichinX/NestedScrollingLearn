package com.example.xujichang.nestedscrollinglearn;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xujichang on 2016/7/21.
 */
public class NestedLayout extends LinearLayout implements NestedScrollingParent, NestedScrollingChild {
    private CustomNestedScrollingChildHelper childHelper;
    private CustomNestedScrollingParentHelper parentHelper;
    private String TAG = "NESTED_SCROLLING";

    public NestedLayout(Context context) {
        this(context, null);

    }

    public NestedLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        parentHelper = new CustomNestedScrollingParentHelper(this);
        childHelper = new CustomNestedScrollingChildHelper(this);
    }

    /**
     * 作为子类时的方法==========================================================================
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
        childHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        childHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPrePerformAccessibilityAction(int action, Bundle arguments) {
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
    /**
     * 子View 开始滑动的回调
     *
     * @param child            子View
     * @param target           支持嵌套滑动的View
     * @param nestedScrollAxes 滑动方向
     * @return 是否支持滑动嵌入 也可以理解是否开启支持嵌入式滑动的开关
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.v(TAG, "in      onStartNestedScroll");
        return true;
    }

    /**
     * 停止滚动
     *
     * @param child
     */
    @Override
    public void onStopNestedScroll(View child) {
        Log.v(TAG, "in      onStopNestedScroll");

        parentHelper.onStopNestedScroll(child);
    }

    /**
     * @param target
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /**
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    /**
     * 滑动之前
     *
     * @param target   支持嵌入式滑动的View
     * @param dx       此次滑动x方向的距离
     * @param dy       此次滑动Y方向的距离
     * @param consumed 在此方法中处理的距离（消费）
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        consumed[0] = dx / 2;
        consumed[1] = dx / 2;
        Log.v(TAG, "in      onNestedPreScroll");

    }

    @Override
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        return super.onNestedPrePerformAccessibilityAction(target, action, args);
    }

    /**
     * 滑动之后
     *
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.v(TAG, "in      onNestedScroll");

    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
