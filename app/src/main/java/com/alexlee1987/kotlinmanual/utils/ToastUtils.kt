package com.alexlee1987.kotlinmanual.utils

import android.content.Context
import android.widget.Toast

/**
 * Toast工具类，可以避免频繁弹出Toast引起的不消失问题
 */
object ToastUtils {
    var sToast: Toast? = null
    fun showShort(context: Context, resId: Int) {
        if (sToast == null) {
            sToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT)
        } else {
            sToast!!.setText(resId)
            sToast!!.duration = Toast.LENGTH_SHORT
        }
        sToast!!.show()
    }

    fun showShort(context: Context, text: CharSequence) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        } else {
            sToast!!.setText(text)
            sToast!!.duration = Toast.LENGTH_SHORT
        }
        sToast!!.show()
    }

    fun showLong(context: Context, resId: Int) {
        if (sToast == null) {
            sToast = Toast.makeText(context, resId, Toast.LENGTH_LONG)
        } else {
            sToast!!.setText(resId)
            sToast!!.duration = Toast.LENGTH_LONG
        }
        sToast!!.show()
    }

    fun showLong(context: Context, text: CharSequence) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        } else {
            sToast!!.setText(text)
            sToast!!.duration = Toast.LENGTH_LONG
        }
        sToast!!.show()
    }
}