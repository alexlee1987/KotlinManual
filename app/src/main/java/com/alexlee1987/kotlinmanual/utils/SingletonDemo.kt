package com.alexlee1987.kotlinmanual.utils

/**
 * 单例模式实现
 */
class SingletonDemo private constructor(){
    companion object {
        // Kotlin写法，线程安全
        val INSTANCE: SingletonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo()
        }
    }

    fun add(num1: Int, num2: Int): Int{
        return num1 + num2
    }

    /**
     * Lambda表达式定义求和方法
     */
    val sum = {num1: Int, num2: Int -> num1 + num2}
}