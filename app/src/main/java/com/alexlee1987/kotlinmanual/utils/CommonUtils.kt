package com.alexlee1987.kotlinmanual.utils

/**
 * Kotlin定义含有部分静态方法的类
 */
class CommonUtils {
    companion object {
        // 伴生常量：在 companion object 中的公共、非 const 的属性 实际上为常量 必须用 @JvmField 注解才能暴露为静态字段。
        // 如果没有这个注解，这些属性只能作为静态 Companion 字段中奇怪命名的 ‘getters’ 实例。
        // 而只使用 @JvmStatic 而不是 @JvmField 的话，会将奇怪命名的 ‘getters’ 移到类的静态方法中，但仍然是不正确的。
        @JvmField
        var times: Int = 0
        // 伴生函数（伴生方法）：在 “companion object” 中的公共函数必须用使用 @JvmStatic 注解才能暴露为静态方法。
        // 如果没有这个注解，这些函数仅可用作静态 Companion 字段上的实例方法。
        @JvmStatic
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