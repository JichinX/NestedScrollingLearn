package com.example.nestedview.Base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.nestedview.R;

/**
 * Created by xujichang on 2016/8/17.
 */
public class NestedParentView extends LinearLayout implements NestedScrollingParent {

    public static final int NO_TYPE = -1;
    public static final int TYPE_SCROLL = 0;
    public static final int TYPE_FADE = 1;

    /**
     * 触发滚动事件的Child
     */
    private View scrolledView;
    /**
     * 可以消失的View
     *
     * @param context
     */
    private View fadableView;

    private int count = 0;

    private int fadeViewH;
    private OverScroller mScroller;

    private NestedScrollingParentHelper scrollingParentHelper;

    public NestedParentView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedParentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scrollingParentHelper = new NestedScrollingParentHelper(this);
        mScroller = new OverScroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childs = getChildCount();
        int heightExpectScroll = 0;
        int height = 0;
        for (int i = 0; i < childs; i++) {
            count++;
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            int id = params.getTypeId();

            if (id == TYPE_FADE) {
                if (i == 0) {
                    fadableView = child;
                    fadeViewH = fadableView.getMeasuredHeight();
                } else {
                    throw new IllegalStateException("only the first child can be a fadeView!");
                }
            } else if (id == TYPE_SCROLL) {
                if (i == childs - 1) {
                    scrolledView = child;
                    params.height = getMeasuredHeight() - heightExpectScroll;
                } else {
                    throw new IllegalStateException("only the last child can be a scrollView!");
                }
            } else {
                heightExpectScroll += child.getMeasuredHeight();
            }
            height += child.getMeasuredHeight();
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        } else if (p instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams && super.checkLayoutParams(p);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        private int typeId;

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            final TypedArray a = c.obtainStyledAttributes(attrs,
                    R.styleable.NestedParentView);
            typeId = a.getInteger(R.styleable.NestedParentView_viewType, NO_TYPE);

            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
        }

    }

    /**
     * ***************************nestedScroll相关方法********************
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //TODO 实现滑动嵌套 的主要处理方法
        boolean showing = dy < 0 && getScaleY() > 0 && !ViewCompat.canScrollVertically(target, -1);
        boolean hidding = dy > 0 && getScrollY() < fadeViewH;
        if (showing || hidding) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
//        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        if (getScrollY() >= fadeViewH) return false;
//        fling((int) velocityY);
//        return true;
        return false;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {

        return false;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //true 允许 共同消费滑动事件
        return true;
    }

    @Override
    public void onStopNestedScroll(View child) {
        scrollingParentHelper.onStopNestedScroll(child);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > fadeViewH) {
            y = fadeViewH;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        if (null != fadableView) {
//            fadeViewH = fadableView.getMeasuredHeight();
//        }
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, fadeViewH);
        invalidate();
    }

}
