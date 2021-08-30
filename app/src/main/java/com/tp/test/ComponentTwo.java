package com.tp.test;

import android.util.Log;
import android.widget.TextView;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: 功能2
 * Author: zl
 */
public class ComponentTwo extends FragmentDecorator {

    private TextView textView;

    public ComponentTwo(Component fragment) {
        super(fragment);
    }

    @Override
    public void enter() {
        super.enter();
        Log.e("component", "two enter");
        textView = findViewById(R.id.text_view2);
        if (textView != null) {
            textView.setText("Component is Two");
        }

    }

    @Override
    public void exit() {
        super.exit();
        Log.e("component", "two enter");
    }
}
