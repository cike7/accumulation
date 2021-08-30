package com.tp.test.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;


/**
 * Copyright (C) 王字旁的理
 * Date: 8/16/2021
 * Description: 渐变颜色字体
 * Author: zl
 */
public class GradientTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint paint;

    private @ColorInt int mStartColor;

    private @ColorInt int mEndColor;

    public GradientTextView(Context context) {
        this(context,null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        paint = getPaint();
        final LinearGradient linearGradient = new LinearGradient(
                0, 0, getMeasuredWidth(), 0,
                new int[]{Color.RED, Color.GREEN},
                new float[]{0, 0.8f},
                Shader.TileMode.CLAMP);

        paint.setTextSize(getTextSize());
        //添加渲染
        paint.setShader(linearGradient);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制渐变颜色字体
        //canvas.drawText(getText(),0,getText().length(),0,getMeasuredHeight() - paint.getFontMetrics().bottom,paint);

        //canvas.drawCircle(getMeasuredWidth() / 2,getMeasuredHeight() - paint.getFontMetrics().bottom,5f,paint);

    }

    public void setStartColor(int startColor) {
        this.mStartColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.mEndColor = endColor;
    }
}
