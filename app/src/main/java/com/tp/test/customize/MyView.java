package com.tp.test.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Copyright (C) 王字旁的理
 * Date: 8/26/2021
 * Description:
 * Author: zl
 */
public class MyView extends View {

    private Paint mPaint;

    private Paint mPaint2;

    private Paint mTextPaint;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(1);
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.GREEN);

    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return super.dispatchGenericMotionEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float mXCenter = getWidth() / 2;
        float mYCenter = getHeight();

        float radius = mXCenter;

        RectF rectF = new RectF();
        rectF.left = mXCenter - radius;
        rectF.top = mYCenter - radius;
        rectF.right = radius * 2 + (mXCenter - radius);
        rectF.bottom = radius * 2 + (mYCenter - radius);

        canvas.drawArc(rectF, -180, 180, false, mPaint);

        float radius2 = mXCenter - 50;

        RectF rectF2 = new RectF();
        rectF2.left = mXCenter - radius2;
        rectF2.top = mYCenter - radius2;
        rectF2.right = radius2 * 2 + (mXCenter - radius2);
        rectF2.bottom = radius2 * 2 + (mYCenter - radius2);

        canvas.drawArc(rectF2, -180, 180, false, mPaint2);

        canvas.drawCircle(mXCenter, mYCenter - (mYCenter / 2), 50, mPaint);

        String text = "100%";
        Rect textRect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), textRect);
        canvas.drawText("100%", mXCenter - (textRect.width() / 2), mYCenter - (mYCenter / 2), mPaint);


        String textNumber = "0";
        mTextPaint.getTextBounds(textNumber, 0, textNumber.length(), textRect);

        float centX = mXCenter - (textRect.width() / 2);
        float centY = mYCenter - (mYCenter / 2);

        int count = 20;

        for (int i = 0; i < count; i++) {
            if (i < count / 2){
                canvas.drawText(  ".", centX + (i * 30) - (textRect.left + textRect.right), centY - (i * 30) - (textRect.left + textRect.right) * count / 2, mTextPaint);

            }else {
                canvas.drawText(  ".", centX + (i * 30) - (textRect.left + textRect.right) * count / 2, centY + (i * 30) - (textRect.left + textRect.right) * count / 2, mTextPaint);

            }
        }

    }
}
