package com.tp.test.fragment;

import android.util.Log;
import android.widget.TextView;

import com.tp.test.R;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: 功能3
 * Author: zl
 */
public class ComponentThree extends FragmentDecorator {

    private TextView textView;

    public ComponentThree(Component fragment) {
        super(fragment);
    }

    @Override
    public void enter() {
        super.enter();
        Log.e("component", "three enter");
        textView = (TextView) findViewById(R.id.text_view3);
        if(textView!=null){
            textView.setText("Component is Three");
        }

    }

    @Override
    public void exit() {
        super.exit();
        Log.e("component", "three enter");
    }
}
