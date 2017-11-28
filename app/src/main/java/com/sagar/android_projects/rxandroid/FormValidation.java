package com.sagar.android_projects.rxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sagar.android_projects.rxandroid.databinding.ActivityFormValidationBinding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class FormValidation extends AppCompatActivity {

    private ActivityFormValidationBinding activityFormValidationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_validation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityFormValidationBinding = DataBindingUtil.setContentView(this, R.layout.activity_form_validation);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //for credit card number
        Observable<Boolean> creditCardObservable = RxTextView.textChanges(activityFormValidationBinding.contentFormValidation.edittextCardNumber)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return (charSequence.length() == 10);
                    }
                })
                .distinctUntilChanged();

        Observer<Boolean> observerCard = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(MainActivity.TAG, "Result card : " + aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        creditCardObservable.subscribe(observerCard);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //for name
        Observable<Boolean> nameObservable = RxTextView.textChanges(activityFormValidationBinding.contentFormValidation.edittextName)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return (charSequence.length() == 5);
                    }
                })
                .distinctUntilChanged();

        Observer<Boolean> observerName = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(MainActivity.TAG, "Result name : " + aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        nameObservable.subscribe(observerName);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //combine latest validation
        Observable.combineLatest(
                creditCardObservable,
                nameObservable,
                new BiFunction<Boolean, Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean aBoolean, Boolean aBoolean2) throws Exception {
                        return (aBoolean && aBoolean2);
                    }
                }
        )
                .subscribe(
                        new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                Log.i(MainActivity.TAG, "validation : " + aBoolean);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

}
