package com.alexlee1987.kotlinmanual.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

/**
 * RxJava接入示例
 * created by alexlee1987 on 2018/10/16
 */
class RxJavaDemoActivity : AppCompatActivity() {
    private val TAG = RxJavaDemoActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            textView {
                textSize = 24f
                text = "RxJava2Demo"
            }.lparams {
                width = matchParent
                height = wrapContent
            }
        }

        initObservable2()
    }

    /**
     * 普通方式的RxJava实现
     */
    private fun initObservable() {
        // 1.创建被观察者 Observable 对象
        val observable = Observable.create(ObservableOnSubscribe<Int> { e ->
            // 第一步：初始化Observable
            Log.i(TAG, "Observable emit 1" + "\n")
            e.onNext(1)
            Log.i(TAG, "Observable emit 2" + "\n")
            e.onNext(2)
            Log.i(TAG, "Observable emit 3" + "\n")
            e.onNext(3)
            e.onComplete()
            Log.i(TAG, "Observable emit 4" + "\n")
            e.onNext(4)
        })
        // 2.创建观察者 Observer 对象
        val observer = object : Observer<Int> { // 第三步：订阅
            // 第二步：初始化Observer
            private var i: Int = 0
            private var mDisposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "开始采用subscribe连接")
                mDisposable = d
            }

            override fun onNext(integer: Int) {
                Log.i(TAG, "对Next事件作出响应" + integer)
                i++
//                if (i == 2) {
//                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
//                    mDisposable!!.dispose()
//                }
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError : value : " + e.message + "\n")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete" + "\n")
            }
        }
        // 3.通过订阅（Subscribe）连接观察者和被观察者
        observable.subscribe(observer)
    }

    /**
     * 基于事件流的链式调用RxJava实现，建议使用方式
     */
    private fun initObservable2() {
        Observable.create(ObservableOnSubscribe<Int> { e ->
            // 第一步：初始化Observable
            Log.i(TAG, "Observable emit 1" + "\n")
            e.onNext(1)
            Log.i(TAG, "Observable emit 2" + "\n")
            e.onNext(2)
            Log.i(TAG, "Observable emit 3" + "\n")
            e.onNext(3)
            e.onComplete()
            Log.i(TAG, "Observable emit 4" + "\n")
            e.onNext(4)
        }).subscribe(object : Observer<Int> { // 第三步：订阅
            // 第二步：初始化Observer
            private var i: Int = 0
            private var mDisposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "开始采用subscribe连接")
                mDisposable = d
            }

            override fun onNext(integer: Int) {
                Log.i(TAG, "对Next事件作出响应" + integer)
                i++
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件，即切断了观察者和被观察者之间的连接
                    // 切断连接之后，观察者不再响应被观察者的变化，但被观察者还是可以继续发送事件
                    mDisposable!!.dispose()
                }
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError : value : " + e.message + "\n")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete" + "\n")
            }
        })
    }
}
