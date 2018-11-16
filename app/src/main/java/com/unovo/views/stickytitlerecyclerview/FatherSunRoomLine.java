package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class FatherSunRoomLine extends View {
    private Paint mPaint;
    private Path mPath;
    private int mColor;

    public FatherSunRoomLine(Context context) {
        this(context,null);
    }

    public FatherSunRoomLine(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FatherSunRoomLine);
        int lineStyle = ta.getInt(R.styleable.FatherSunRoomLine_line_style, 1);
        mColor = ta.getColor(R.styleable.FatherSunRoomLine_line_Color, Color.GRAY);
        ta.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        // 需要加上这句，否则画不出东西
        mPaint.setStyle(Paint.Style.STROKE);
        if(lineStyle==2){
            mPaint.setPathEffect(new DashPathEffect(new float[] {getResources().getDimensionPixelSize(R.dimen.x98), getResources().getDimensionPixelSize(R.dimen.x11)}, 0));
        }

        mPath = new Path();
    }
    public void setLineStyle(LineStyle lineStyle){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        // 需要加上这句，否则画不出东西
        mPaint.setStyle(Paint.Style.STROKE);
        if (lineStyle==LineStyle.DASH){
            mPaint.setPathEffect(new DashPathEffect(new float[] {getResources().getDimensionPixelSize(R.dimen.x98), getResources().getDimensionPixelSize(R.dimen.x11)}, 0));
        }
        invalidate();

    }
    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        mPaint.setStrokeWidth(getHeight());
        mPath.reset();
        mPath.moveTo(0, centerY);
        mPath.lineTo(getWidth(), centerY);
        canvas.drawPath(mPath, mPaint);
    }
    public enum LineStyle {
        NORMAL,DASH
    }

}
