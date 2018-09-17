package com.weylan.blog;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.Date;

public class RxTest {

    public static void main(String[] args) throws InterruptedException {
//        Flowable.just("hello world").subscribe(System.out::println);
//        Flowable<Integer> filter = Flowable.range(1, 5).map(v -> v * v).filter(v -> v % 3 == 0);
//        filter.subscribe(System.out::println);
//        Disposable subscribe = Observable.create(emitter -> {
//            while (!emitter.isDisposed()) {
//                long time = System.currentTimeMillis();
//                emitter.onNext(time);
//                if (time % 2 != 0) {
//                    emitter.onError(new IllegalStateException("odd millisecond!"));
//                    break;
//                }
//            }
//        }).subscribe(System.out::println, Throwable::printStackTrace);

//        Flowable.fromCallable(()->{
//            Thread.sleep(1000);
//            return "Done";
//        })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.single())
//                .subscribe(System.out::println,Throwable::printStackTrace);
//        Thread.sleep(2000);
//        Flowable.range(1, 10)
//                .observeOn(Schedulers.computation())
//                .map(v -> v * v)
//                .blockingSubscribe(System.out::println);
//        Flowable.range(1,10).observeOn(Schedulers.computation()).subscribe(System.out::println);
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
//        Flowable.range(1, 10)
//                .flatMap(v ->
//                        Flowable.just(v)
//                                .subscribeOn(Schedulers.computation())
//                                .map(w -> w * w)
//                )
//                .blockingSubscribe(System.out::println);

    }

}
