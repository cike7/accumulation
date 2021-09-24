package com.tp.test.fragment;

import android.util.Log;
import android.widget.TextView;

import com.tp.test.R;
import com.tp.test.annotation.GetFragment;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: 功能4
 * Author: zl
 */
@GetFragment(4)
public class ComponentFour extends FragmentDecorator {

    private TextView textView;

    public ComponentFour(Component fragment) {
        super(fragment);
    }

    @Override
    public void enter() {
        super.enter();
        Log.e("component", "four enter");
        textView = (TextView) findViewById(R.id.text_view4);
        if (textView != null) {
            textView.setText("Component is four");
        }

    }

    @Override
    public void exit() {
        super.exit();
        Log.e("component", "four enter");
    }
}
