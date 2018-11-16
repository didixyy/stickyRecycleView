package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RoomStatusDayTextView extends AppCompatTextView {

    private Paint mPaint;
    private Paint mPaint2;
    private RectF mRectF;
    private RectF mRectF2;
    public RoomStatusDayTextView(Context context) {
        this(context,null);
    }

    public RoomStatusDayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        mPaint = new Paint();

        mPaint.setColor(getResources().getColor(R.color.assist_color_red_ee776f));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {



        mRectF = new RectF(0,0,this.getWidth(),this.getHeight());
        canvas.drawRoundRect(mRectF,1,1,mPaint);


        mRectF2 = new RectF(3,0,this.getWidth()-3,this.getHeight()-3);
        canvas.drawRoundRect(mRectF2,1,1,mPaint2);
        canvas.save();
        super.onDraw(canvas);
         canvas.restore();
    }
}
