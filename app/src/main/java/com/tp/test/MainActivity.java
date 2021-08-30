package com.tp.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.badge.BadgeDrawable;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FragmentDecorator component = new ComponentFour(new ComponentThree(new ComponentTwo(new ComponentOne(null))));
//        BaseFragment fragment = new BaseFragment.Builder(component).create(R.layout.fragment_one);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragment).commit();

    }

}