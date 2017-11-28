package com.sagar.android_projects.rxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jakewharton.rxbinding2.view.RxView;
import com.sagar.android_projects.rxandroid.databinding.ActivityBufferBinding;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class Buffer extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityBufferBinding activityBufferBinding;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityBufferBinding = DataBindingUtil.setContentView(this, R.layout.activity_buffer);

        RxView.clicks(activityBufferBinding.contentBuffer.button)
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return "got a click";
                    }
                })
                .buffer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        if (strings.size() > 0)
                            Log.i(MainActivity.TAG, "onNext: " + strings.size());
                        else
                            Log.i(MainActivity.TAG, "got nothing");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        disposable.dispose();
        finish();
    }
}
