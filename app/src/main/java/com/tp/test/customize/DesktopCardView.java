package com.tp.test.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tp.test.R;
import com.tp.test.model.DesktopCardModel;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/6
 * Description: 桌面卡片封装类
 * Author: zl
 */
public class DesktopCardView extends LinearLayout {

    private ImageView imageCard;

    private TextView textCheck,textComment,textTitle,textName;

    private DesktopCardModel mModel;

    public DesktopCardView(Context context) {
        this(context,null);
    }

    public DesktopCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DesktopCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_desktop_card,this,true);

        imageCard = findViewById(R.id.img_item_desktop_card);

        textCheck = findViewById(R.id.text_item_desktop_card_check);
        textComment = findViewById(R.id.text_item_desktop_card_comment);
        textTitle = findViewById(R.id.text_item_desktop_card_title);
        textName = findViewById(R.id.text_item_desktop_card_name);

    }

    public void setModel(DesktopCardModel model){

        mModel = model;

        imageCard.setImageResource(R.mipmap.img_card1);

        textCheck.setText(String.valueOf(model.getComment()));
        textComment.setText(String.valueOf(model.getComment()));
        textTitle.setText(model.getTitle());
        textName.setText(model.getName());

    }


    public DesktopCardModel getModel() {
        return mModel;
    }
}
