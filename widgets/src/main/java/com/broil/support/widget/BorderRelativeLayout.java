
package com.broil.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 可以想Html中的div一样设置border的属性
 *
 * @author skg
 */
public class BorderRelativeLayout extends RelativeLayout {
    private final static int DEFAULT_BORDER_SIZE = 1;
    private int mBorderColor = Color.RED;
    private int mBoderLeftSize = DEFAULT_BORDER_SIZE;
    private int mBoderTopSize = DEFAULT_BORDER_SIZE;
    private int mBoderRightSize = DEFAULT_BORDER_SIZE;
    private int mBoderBottomSize = DEFAULT_BORDER_SIZE;

    private int mBoderBottomLeftPadding = 0;

    private boolean isBorderEnable = true;;
    private Paint borderPaint;

    public BorderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.Border, 0, 0);
        mBorderColor = a.getColor(R.styleable.Border_border_b_color,
                Color.RED);
        mBoderLeftSize = a.getDimensionPixelSize(
                R.styleable.Border_border_left_size,
                DEFAULT_BORDER_SIZE);
        mBoderTopSize = a.getDimensionPixelSize(
                R.styleable.Border_border_top_size,
                DEFAULT_BORDER_SIZE);
        mBoderRightSize = a.getDimensionPixelSize(
                R.styleable.Border_border_right_size,
                DEFAULT_BORDER_SIZE);
        mBoderBottomSize = a.getDimensionPixelSize(
                R.styleable.Border_border_bottom_size,
                DEFAULT_BORDER_SIZE);
        isBorderEnable = a.getBoolean(
                R.styleable.Border_border_enable, true);
        mBoderBottomLeftPadding = a.getDimensionPixelSize(
                R.styleable.Border_border_bottom_leftpadding,
                0);
        a.recycle();
        init();
    }

    public BorderRelativeLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        borderPaint = new Paint();
        borderPaint.setColor(mBorderColor);
    }

    public void setBorderSize(int left, int top, int right, int bottom) {
        mBoderLeftSize = left;
        mBoderTopSize = top;
        mBoderRightSize = right;
        mBoderBottomSize = bottom;
        invalidate();
    }

    public void setBorderColor(int color) {
        mBorderColor = color;
        borderPaint.setColor(mBorderColor);
        invalidate();
    }

    public boolean isBorderEnable() {
        return isBorderEnable;
    }

    public void setBorderEnable(boolean enable) {
        if (enable == isBorderEnable) {
            return;
        }
        isBorderEnable = enable;
        invalidate();
    }

    private Rect tempRect = new Rect();

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!isBorderEnable) {
            return;
        }
        // draw border
        int vw = getWidth();
        int vh = getHeight();
        if (mBoderLeftSize > 0) {
            tempRect.setEmpty();
            tempRect.set(0, 0, mBoderLeftSize, vh);
            canvas.drawRect(tempRect, borderPaint);
        }
        if (mBoderTopSize > 0) {
            tempRect.setEmpty();
            tempRect.set(0, 0, vw, mBoderTopSize);
            canvas.drawRect(tempRect, borderPaint);
        }
        if (mBoderRightSize > 0) {
            tempRect.setEmpty();
            tempRect.set(vw - mBoderRightSize, 0, vw, vh);
            canvas.drawRect(tempRect, borderPaint);
        }
        if (mBoderBottomSize > 0) {
            tempRect.setEmpty();
            tempRect.set(mBoderBottomLeftPadding, vh - mBoderBottomSize, vw, vh);
            canvas.drawRect(tempRect, borderPaint);
        }
    }
}
