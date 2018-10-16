package com.alexlee1987.kotlinmanual

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.alexlee1987.kotlinmanual.activity.RxJavaDemoActivity
import com.alexlee1987.kotlinmanual.ankodemo.AnkoDemoActivity
import com.alexlee1987.kotlinmanual.recyclerview.RecyclerViewActivity
import com.alexlee1987.kotlinmanual.utils.CommonUtils
import com.alexlee1987.kotlinmanual.utils.SingletonDemo
import com.alexlee1987.kotlinmanual.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity(), OnClickListener {
    // 常量定义
    private val TAG = MainActivity::class.java.simpleName
    // lateinit修饰的变量定义：延迟初始化
    lateinit var clazz: Class<*>
    override fun onClick(view: View?) {
        if (view == null) {
            return
        }
        when(view.id) {
            R.id.btn_singleton -> {
                ToastUtils.showShort(this, "单例模式示例运算结果为：" + SingletonDemo.INSTANCE.add(8, 110).toString())
//                ToastUtils.showShort(this, "单例模式示例运算结果为：" + SingletonDemo.INSTANCE.sum(8, 120).toString())
//                ToastUtils.showShort(this, "单例模式示例运算结果为：" + CommonUtils().sum(8, 120).toString())
                return
            }
            R.id.btn_static_fun ->{
                var res: String = getText(R.string.str_static_fun).toString() + ": " + CommonUtils.getCurTimes().toString() + "ababab".formatted()
                ToastUtils.showShort(this, res)
                return
            }
            R.id.btn_recyclerview -> {
                clazz = RecyclerViewActivity::class.java
            }
            R.id.btn_anko -> {
                clazz = AnkoDemoActivity::class.java
            }
            R.id.btn_rxjava -> {
                clazz = RxJavaDemoActivity::class.java
            }
            else -> clazz = MainActivity::class.java
        }
        if (clazz != null) {
            startActivity(Intent(this, clazz))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        // Kotlin写法，不需要findViewById
        btn_singleton.setOnClickListener(this)
        btn_static_fun.setOnClickListener(this)
        btn_recyclerview.setOnClickListener(this)
        btn_anko.setOnClickListener(this)
        btn_rxjava.setOnClickListener(this)
    }

    /**
     * kotlin扩展函数：扩展String类的方法，有可能是String已有的，也有可能是Sting没有的
     */
    private fun String.formatted(): String {
        return this.replace('a', 'b')
    }
    
}
