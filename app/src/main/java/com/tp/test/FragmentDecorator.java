package com.tp.test;

import android.util.Log;
import android.view.View;

import androidx.annotation.CallSuper;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: fragment装饰器
 * Author: zl
 */
public class FragmentDecorator implements FragmentComponent{

    /**
     * 控件
     */
    private Component mComponent;

    /**
     * 当前View
     */
    private View mView;

    public FragmentDecorator(Component component){
        mComponent = component;
    }

    @CallSuper
    @Override
    public void enter() {
        Log.e("component","Decorator enter");
        if(mComponent instanceof FragmentDecorator){
            ((FragmentComponent)mComponent).enter();
        }
    }

    @CallSuper
    @Override
    public void exit() {
        Log.e("component","Decorator enter");
        if(mComponent instanceof FragmentDecorator) {
            ((FragmentComponent) mComponent).exit();
        }
    }

    /**
     * 设置view
     * @param view
     */
    public void setView(View view) {
        this.mView = view;
        if(mComponent instanceof FragmentDecorator) {
            ((FragmentDecorator) mComponent).setView(view);
        }
    }

    public <T extends View> T findViewById(int i) {
        if (mView != null) {
            return mView.findViewById(i);
        }
        return null;
    }

}