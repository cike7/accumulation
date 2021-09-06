package com.tp.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tp.test.adapter.DesktopCardAdapter;
import com.tp.test.customize.DesktopCardView;
import com.tp.test.model.DesktopCardModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        ArrayList<DesktopCardModel> cardModels = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            DesktopCardModel item = new DesktopCardModel();
            item.setCheck(i);
            item.setComment(i);
            item.setName("作者" + i);
            item.setId(i);
            cardModels.add(item);
        }

        DesktopCardAdapter adapter = new DesktopCardAdapter(this, cardModels);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DesktopCardView item = (DesktopCardView) parent.getChildAt(position);

                Log.e("home", ">" + item.getModel().getName());
            }
        });


        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DesktopCardView item = (DesktopCardView) parent.getChildAt(position);

                Log.e("home", "item" + item.getModel().getName() + "position:" + position + "id:" + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("home", "position" + parent.toString());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

//        FragmentDecorator component = new ComponentFour(new ComponentThree(new ComponentTwo(new ComponentOne(null))));
//        BaseFragment fragment = new BaseFragment.Builder(component).create(R.layout.fragment_one);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragment).commit();

    }

}