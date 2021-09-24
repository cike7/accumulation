package com.tp.test.annotation;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/7
 * Description: 实现注解绑定Properties字段
 * Author: zl
 */
public class AutoFragment {

    /**
     * 实现注解绑定Properties文件的字段
     * @param activity 当前Activity
     */
    public static void bind(Activity activity){
//        // 获取类信息
//        Class<? extends Context> aClass = activity.getClass();
//        // 获取所有属性
//        Class[] declaredFields = aClass.getDeclaredClasses();
//        if (declaredFields.length == 0) {
//            return;
//        }

//        for (Class field : declaredFields) {
//            // 获取属性上的注解
////            GetFragment annotation = field.getClasses(GetFragment.class);
////            if (annotation != null) {
////                //获取注解的值
////                int key = annotation.value();
////
////            }
//        }

        List<Class<?>> classes = scan(activity,"com.tp.test");

        Log.e("home","class size> " + classes.size());

        for (int i = 0; i < classes.size(); i++) {
            Log.e("home","class >" + classes.get(i).getName());
        }

    }


    public static List<Class<?>> scan(Context ctx, String entityPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        try {
            PathClassLoader classLoader = (PathClassLoader) Thread
                    .currentThread().getContextClassLoader();

            DexFile dex = new DexFile("com.tp.test.fragment");
            Enumeration<String> entries = dex.entries();
            while (entries.hasMoreElements()) {
                String entryName = entries.nextElement();

                Log.e("home","entryName >" + entryName);

                if (entryName.contains(entityPackage)) {
                    Class<?> entryClass = Class.forName(entryName, true,classLoader);
                    GetFragment annotation = entryClass.getAnnotation(GetFragment.class);
                    if (annotation != null) {
                        classes.add(entryClass);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

}
