package com.tp.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tp.test.R;
import com.tp.test.customize.DesktopCardView;
import com.tp.test.model.DesktopCardModel;

import java.util.ArrayList;

/**
 * Copyright (C), 2003-2021, 深圳市图派科技有限公司
 * Date: 2021/9/6
 * Description:
 * Author: zl
 */
public class DesktopCardAdapter extends BaseAdapter {

    private Context mContent;

    private ArrayList<DesktopCardModel> mCardModels;

    public DesktopCardAdapter(Context context, ArrayList<DesktopCardModel> cardModels){
        mContent = context;
        mCardModels = cardModels;
    }

    @Override
    public int getCount() {
        return mCardModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mCardModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCardModels.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DesktopCardView cardView = new DesktopCardView(mContent);

        cardView.setModel(mCardModels.get(position));

        return cardView;
    }

}
