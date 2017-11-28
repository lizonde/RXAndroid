package com.sagar.android_projects.rxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.sagar.android_projects.rxandroid.databinding.ActivityRetrofitBinding;
import com.sagar.android_projects.rxandroid.singleton.AppSingleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Retrofit extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal,unused")
    private ActivityRetrofitBinding activityRetrofitBinding;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityRetrofitBinding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit);

        ((AppSingleton) getApplicationContext())
                .getApIInterface()
                .getResponse("2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<com.sagar.android_projects.rxandroid.retrofit.models.Response>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable = d;
                                Log.i(MainActivity.TAG, "onSubscribe: ");
                            }

                            @Override
                            public void onNext(com.sagar.android_projects.rxandroid.retrofit.models.Response response) {
                                Log.i(MainActivity.TAG, "onNext: " + response.getPage());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(MainActivity.TAG, "onError: ");
                            }

                            @Override
                            public void onComplete() {
                                disposable.dispose();
                                Log.i(MainActivity.TAG, "onComplete: " + disposable.isDisposed());
                            }
                        }
                );
    }
}
