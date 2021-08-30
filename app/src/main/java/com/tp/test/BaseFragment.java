package com.tp.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/8/13
 * Description: 被组件组件，保留最原始功能
 * Author: zl
 */
public class BaseFragment extends Fragment implements FragmentComponent {

    private int mLayoutID;

    private FragmentDecorator mComponent;

    private View mRootView;

    private BaseFragment(@LayoutRes int layout,FragmentDecorator component) {
        mLayoutID = layout;
        mComponent = component;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(mLayoutID, container, false);
        if(mComponent != null){
            mComponent.setView(mRootView);
        }
        return mRootView;
    }

      @Override
    public void onResume() {
        super.onResume();
        enter();
    }

    @Override
    public void onPause() {
        super.onPause();
        exit();
    }

    @Override
    public void enter() {
        Log.e("component", "base enter");
        if(mComponent != null){
            mComponent.enter();
        }
    }

    @Override
    public void exit() {
        Log.e("component", "base enter");
        if(mComponent != null){
            mComponent.exit();
        }
    }

    /**
     * 建造者模式，防止BaseFragment被修改
     */
    public static class Builder{

        /**
         * 功能
         */
        private FragmentDecorator mDecorator;

        /**
         * 建造者模式，防止BaseFragment被修改
         * @param decorator 需要装饰的功能
         */
        public Builder(FragmentDecorator decorator){
            mDecorator = decorator;
        }

        /**
         * 创建
         * @param layoutId layout xml布局
         * @return BaseFragment
         */
        public BaseFragment create(@LayoutRes int layoutId){
            return new BaseFragment(layoutId,mDecorator);
        }

    }

}