package com.witbit.sherlock.myrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.witbit.sherlock.oneapp.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class MyRxActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_rx);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myOpt2();
			}
		});
	}

	/**
	 * 这个是普通写法，flowable和subscriber都自己定义
	 */
	private void myOpt() {
		Flowable<String> abc;
		Subscriber<String> suber;

		abc = Flowable.create(new FlowableOnSubscribe<String>() {
			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
				e.onNext("sherlock");
				e.onNext("linken");
				e.onComplete();
			}
		}, BackpressureStrategy.BUFFER);

		suber = new Subscriber<String>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(String s) {
				Log.i("sherlock", "MyRxActivity.onNext s == " + s);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		};

		abc.subscribe(suber);
	}

	/**
	 * 这个是用just或者from来自动触发onNext
	 * 还有当subscriber只关心onNext的时候可以直接写出consumer
	 */
	private void myOpt1() {
		Flowable<String> fableJust = Flowable.just("sherlock-Just", "linken-Just");

		String[] ss = {"sherlock-From", "linken-From"};
		Flowable<String> fableFrom = Flowable.fromArray(ss);

		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				Log.i("sherlock", "MyRxActivity.accept s == " + s);
			}
		};

		Consumer<String> consumerTwice = new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				Log.i("sherlock", "MyRxActivity.accept s Twice == " + s + " " + s);
			}
		};

		fableJust.subscribe(consumer);
		fableJust.subscribe(consumerTwice);
		fableFrom.subscribe(consumer);
		fableJust.subscribe(consumerTwice);
	}

	/**
	 * 这里使用map操作符来将一个事件转换成另一个事件
	 * 举个例子就是 onNext("sherlock") 转换成onNext("sherlock-linken")
	 * 或者是 本来是onNext(String) 转换成onNext(int)
	 */
	private void myOpt2() {

		Flowable<String> fable = Flowable.just("sherlock","linken");

		Consumer<String> consumerStr = new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				Log.i("sherlock", "MyRxActivity.accept s == "+s);
			}
		};

		Consumer<Integer> consumerInt = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) throws Exception {
				Log.i("sherlock", "MyRxActivity.accept int == "+integer);
			}
		};

		fable.map(new Function<String, String>() {
			@Override
			public String apply(String s) throws Exception {
				return s+"-hahaha";
			}
		}).subscribe(consumerStr);

		fable.subscribe(consumerStr);

		fable.map(new Function<String, Integer>() {
			@Override
			public Integer apply(String s) throws Exception {
				return s.length();
			}
		}).subscribe(consumerInt);

	}

}
