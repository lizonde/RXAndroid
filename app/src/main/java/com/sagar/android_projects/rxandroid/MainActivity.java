package com.sagar.android_projects.rxandroid;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.sagar.android_projects.rxandroid.adapter.RecyclerviewAdapter;
import com.sagar.android_projects.rxandroid.databinding.ActivityMainBinding;
import com.sagar.android_projects.rxandroid.pojo.RecyclerViewItemPojo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    ArrayList<RecyclerViewItemPojo> recyclerViewItemPojos;

    public static final String TAG = "rx_android_ex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerViewItemPojos = new ArrayList<>();
        recyclerViewItemPojos.add(new RecyclerViewItemPojo("Run on other thread"));
        recyclerViewItemPojos.add(new RecyclerViewItemPojo("Buffer"));
        recyclerViewItemPojos.add(new RecyclerViewItemPojo("DeBounce"));
        recyclerViewItemPojos.add(new RecyclerViewItemPojo("Retrofit"));
        recyclerViewItemPojos.add(new RecyclerViewItemPojo("Form Validation"));

        activityMainBinding.contentMain.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.contentMain.recyclerview.setAdapter(new RecyclerviewAdapter(recyclerViewItemPojos, this));
    }

    public void itemClickedOnRecyclerView(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, RunOnOtherThread.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, Buffer.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Debounce.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Retrofit.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, FormValidation.class));
                break;
        }
    }
}
