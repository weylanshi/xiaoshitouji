package com.weylan.blog;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.junit.Test;

import java.util.TreeMap;
import java.util.concurrent.*;

@Slf4j
public class Rx01 {

    @Test
    public void t1() {
        //关注点1
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                log.debug("subscribe");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                log.debug("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                log.debug(s);
            }

            @Override
            public void onError(Throwable throwable) {
                log.debug("onError");
            }

            @Override
            public void onComplete() {
                log.debug("onComplete");
            }
        };

        observable.subscribe(observer);
    }

    public void t2() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {

        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Test
    public void t3() throws InterruptedException {
//        Observable.create(emmit -> {
//            long start = System.currentTimeMillis();
//            log.info("start observable,{}", start);
//            emmit.onNext(o1());
//            emmit.onNext(o2());
//            emmit.onNext(o3());
//            long end = System.currentTimeMillis();
//            log.info("end use time :{}", end - start);
//        }).subscribeOn(Schedulers.computation()).subscribe(System.out::println, Throwable::printStackTrace);
//        Thread.sleep(3000);
        Observable<Object> ob1 = Observable.create(emitter -> {
            emitter.onNext(o1());
        });
        Observable<Object> ob2 = Observable.create(emitter -> {
            emitter.onNext(o2());
        });
        Observable<Object> ob3 = Observable.create(emitter -> {
            emitter.onNext(o3());
        });
        Observable.merge(ob1, ob2, ob3).subscribeOn(Schedulers.computation()).subscribe(System.out::println, Throwable::printStackTrace);

    }

    private String o1() throws InterruptedException {
        Thread.sleep(1000);
        return "1111";
    }

    private String o2() throws InterruptedException {
        Thread.sleep(1000);
        return "2222";
    }

    private String o3() throws InterruptedException {
        Thread.sleep(1000);
        return "3333";
    }

    @Test
    public void t4() throws InterruptedException {
        Thread main = Thread.currentThread();
        System.out.println(main.getName());
        Executor executor = command -> {

        };
        Observable<String> ob1 = Observable.fromCallable(this::o1);

        Observable<String> ob2 = Observable.fromCallable(this::o2);
        Observable<String> ob3 = Observable.fromCallable(this::o3);

//        Observable.merge(ob1, ob2, ob3)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.newThread())
//                .blockingSubscribe(System.out::println);

//        Thread.sleep(4000);

//        Observable.fromCallable(() -> "call").subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void t5() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Observable.fromFuture(future).subscribeOn(Schedulers.computation()).observeOn(Schedulers.single()).subscribe(System.out::println);
        Thread.sleep(3000);
    }

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("一些耗时操作");
            Thread.sleep(2000);
            return "OK";
        }
    }

}
