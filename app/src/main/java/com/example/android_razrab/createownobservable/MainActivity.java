package com.example.android_razrab.createownobservable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Maybe.create(new PodMaybe()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Log .d ( "MAYB: " ,s );

            }
        }) ;



        Observable.create(new PodObservable()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Log.d( "THRD: " ,s );

            }
        });




        Observable<String> todoObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    String arr[] = new String[]{"a","b","c","d"};

                    List<String> todos = Arrays.asList(arr) ;
                    for (String todo : todos) {
                        emitter.onNext(todo);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        })  ;



        todoObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log .d( "TG: ",s );
            }
        });




        Observable<List<String>> observable = Observable .just(getColorList());
observable. subscribe(new Consumer<List<String>>() {
    @Override
    public void accept(List<String> strings) throws Exception {

  Log .d ("TAGG" , strings.toString());

    }
});

    }


    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        return colors;
    }

}


 class PodObservable implements ObservableOnSubscribe<String> {
     @Override
     public void subscribe(ObservableEmitter<String> e) throws Exception {

         String a[] = new String[]{"a", "b", "c"};
         List<String> stringList = Arrays.asList(a);

         for (String abc : stringList) {

             e.onNext(abc);
         }


     }

 }

     class PodMaybe implements MaybeOnSubscribe <String> {

         @Override
         public void subscribe(MaybeEmitter<String> e) throws Exception {

             e.onSuccess("Stroka");

         }
     }
