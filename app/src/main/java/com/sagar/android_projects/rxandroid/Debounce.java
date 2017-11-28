package com.sagar.android_projects.rxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sagar.android_projects.rxandroid.databinding.ActivityDebounceBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class Debounce extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityDebounceBinding activityDebounceBinding;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityDebounceBinding = DataBindingUtil.setContentView(this, R.layout.activity_debounce);

        RxTextView.textChanges(activityDebounceBinding.contentDebounce.edittext)
                .debounce(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.i(MainActivity.TAG, "onNext: " + charSequence);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(MainActivity.TAG, "onComplete: ");
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(MainActivity.TAG, "onBackPressed: before " + disposable.isDisposed());
        disposable.dispose();
        Log.i(MainActivity.TAG, "onBackPressed: after " + disposable.isDisposed());
    }
}
