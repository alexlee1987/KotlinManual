package com.alexlee1987.kotlinmanual.utils

/**
 * Kotlin定义含有部分静态方法的类
 */
class CommonUtils {
    companion object {
        var times: Int = 0
        fun getCurTimes(): Int {
            times ++
            return times
        }
    }

    /**
     * Lambda表达式定义求和方法
     */
    val sum = {num1: Int, num2: Int -> num1 + num2}
}