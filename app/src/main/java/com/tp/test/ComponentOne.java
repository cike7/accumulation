package com.tp.test;

import android.util.Log;
import android.widget.TextView;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: 功能1
 * Author: zl
 */
public class ComponentOne extends FragmentDecorator {

    private TextView textView;

    public ComponentOne(Component fragment) {
        super(fragment);
    }

    @Override
    public void enter() {
        super.enter();
        Log.e("component", "one enter");
        textView = (TextView) findViewById(R.id.text_view1);
        if (textView != null) {
            textView.setText("Component is One");
        }

    }

    @Override
    public void exit() {
        super.exit();
        Log.e("component", "one enter");
    }
}