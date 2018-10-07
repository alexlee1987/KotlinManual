package com.alexlee1987.kotlinmanual

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.alexlee1987.kotlinmanual.recyclerview.RecyclerViewActivity
import com.alexlee1987.kotlinmanual.utils.CommonUtils
import com.alexlee1987.kotlinmanual.utils.SingletonDemo
import com.alexlee1987.kotlinmanual.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

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
            }
            R.id.btn_static_fun ->{
                var res: String = getText(R.string.str_static_fun).toString() + ": " + CommonUtils.getCurTimes().toString()
                ToastUtils.showShort(this, res)
            }
            R.id.btn_recyclerview -> {
                clazz = RecyclerViewActivity::class.java
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
    }
    
}
