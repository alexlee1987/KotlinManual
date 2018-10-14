package com.alexlee1987.kotlinmanual.ankodemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alexlee1987.kotlinmanual.MainActivity
import org.jetbrains.anko.*
import java.lang.Thread.sleep

/**
 * anko库各种语法demo
 * created by alexlee1987 on 2018/10/14
 */
class AnkoDemoActivity : AppCompatActivity(), AnkoLogger {
    private val TAG = AnkoDemoActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scrollView {
            verticalLayout {
                padding = dip(30)
                button("打开其他界面：MainActivity"){
                    textSize = 20f
                    lines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    onClick {
                        startActivity<MainActivity>()
                        // Anko写法
//            startActivity<AnkoDemoActivity>("id" to 5, "name" to "John")
//            // 标准Kotlin写法
//            val intent = Intent(this, AnkoDemoActivity::class.java)
//            intent.putExtra("id", 5)
//            intent.putExtra("name", "John")
//            startActivity(intent)
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                var title = textView {
                    text = "这是用anko写的TextView"
                }
                editText {
                    hint = "姓名"
                    textSize = 24f
                }
                button("Login") {
                    textSize = 20f
                    onClick {
                        var message = "点击了登录"
                        title.text = message
                        toast(message)
                        info("$TAG : $message")
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(12)
                }
                seekBar {
                    onSeekBarChangeListener {
                        // 用anko只需要写需要实现的方法，很简洁，与标准语法不同
                        onProgressChanged{ seekbar, progress, fromUser ->
                            toast("当前进度： " + progress + " fromUser: " + fromUser)
                        }
                    }
                }
                button("显示长Toast") {
                    textSize = 20f
                    onClick {
                        longToast("显示长Toast")
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                button("显示短Toast") {
                    textSize = 20f
                    onClick {
                        longToast("显示短Toast")
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                button("显示自定义布局Dialog") {
                    textSize = 20f
                    onClick {
                        // Anko写法：自定义布局弹框Dialog
                        alert {
                            customView {
                                verticalLayout {
                                    val name = editText {
                                        hint = "姓名"
                                    }
                                    val place = editText {
                                        hint = "籍贯"
                                    }
                                    // 取消按钮
                                    negativeButton("取消")
                                    // 确定按钮
                                    positiveButton("注册") {
                                        toast("你注册的信息是名字：" + name.text + "\n籍贯： " + place.text)
                                    }
                                }
                            }
                        }.show()
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                button("浏览器操作"){
                    onClick {
                        // 通过默认浏览器打开指定网页
                        browse("https://www.baidu.com")
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                button("获取服务相关"){
                    onClick {
                        var message = if (wifiManager.isWifiEnabled) "Wifi Enabled" else "Wifi not enabled"
                        toast("Wifi服务状态：$message")
                    }
                }.lparams(width = wrapContent, height = wrapContent)
                button("异步任务执行：async"){
                    onClick {
                        async {
                            // 耗时任务
                            sleep(2000)
                            // 回到ui线程
                            uiThread {
                                title.text = "异步任务执行完成了"
                            }
                        }
                    }
                }.lparams(width = wrapContent, height = wrapContent)
            }
        }
    }
}
