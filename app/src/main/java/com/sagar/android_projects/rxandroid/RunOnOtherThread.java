package com.sagar.android_projects.rxandroid;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.sagar.android_projects.rxandroid.databinding.ActivityRunOnOtherThreadBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RunOnOtherThread extends AppCompatActivity implements Observable {

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityRunOnOtherThreadBinding activityRunOnOtherThreadBinding;
    private PropertyChangeRegistry registry;
    public String updateText;
    public int numberOfProcessRunning = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_on_other_thread);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registry = new PropertyChangeRegistry();
        activityRunOnOtherThreadBinding = DataBindingUtil.setContentView(this, R.layout.activity_run_on_other_thread);
        activityRunOnOtherThreadBinding.setContext(this);
    }

    public void buttonClicked(View v) {
        implementRxAndroid();
    }

    private void implementRxAndroid() {
        io.reactivex.Observable.just("sagar")
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        numberOfProcessRunning++;
                        registry.notifyChange(RunOnOtherThread.this, BR.numberOfProcessRunning);
                        updateText += String.valueOf("\nstarted the service " + numberOfProcessRunning);
                        registry.notifyChange(RunOnOtherThread.this, BR.updateText);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(MainActivity.TAG, "onNext: " + numberOfProcessRunning);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        updateText += String.valueOf("\ncompleted the service " + numberOfProcessRunning);
                        registry.notifyChange(RunOnOtherThread.this, BR.updateText);
                        numberOfProcessRunning--;
                        registry.notifyChange(RunOnOtherThread.this, BR.numberOfProcessRunning);
                    }
                });
    }

    @Bindable
    public String getUpdateText() {
        return updateText;
    }

    @Bindable
    public int getNumberOfProcessRunning() {
        return numberOfProcessRunning;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }

}
