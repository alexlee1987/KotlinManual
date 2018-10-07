package com.alexlee1987.kotlinmanual.utils

import android.content.Context
import android.content.SharedPreferences
import com.alexlee1987.kotlinmanual.KotlinManualApplication

/**
 * SharedPreference工具类
 * Created by alexlee1987 on 18-10-07
 */
object SPUtils {

    fun putStringSet(name: String, key: String, value: MutableSet<String>?) {
        getSPObject(name).edit().putStringSet(key, value)
    }

    fun getStringSet(name: String, key: String): MutableSet<String>? {
        return getStringSet(name, key, null)
    }

    fun getStringSet(name: String, key: String, default: MutableSet<String>?): MutableSet<String>? {
        return getSPObject(name).getStringSet(key, default)
    }

    fun putString(name: String, key: String, value: String) {
        getSPObject(name).edit().putString(key, value).apply()
    }

    fun getString(name: String, key: String): String? {
        return getString(name, key, null)
    }

    fun getString(name: String, key: String, default: String?): String? {
        return getSPObject(name).getString(key, default)
    }

    fun putInt(name: String, key: String, value: Int) {
        getSPObject(name).edit().putInt(key, value).apply()
    }

    fun getInt(name: String, key: String): Int {
        return getInt(name, key, 0)
    }

    fun getInt(name: String, key: String, default: Int): Int {
        return getSPObject(name).getInt(key, default)
    }

    fun putFloat(name: String, key: String, value: Float) {
        getSPObject(name).edit().putFloat(key, value).apply()
    }

    fun getFloat(name: String, key: String): Float {
        return getFloat(name, key, -1F)
    }

    fun getFloat(name: String, key: String, defValue: Float): Float {
        return getSPObject(name).getFloat(key, defValue)
    }

    fun putLong(name: String, key: String, value: Long) {
        getSPObject(name).edit().putLong(key, value).apply()
    }

    fun getLong(name: String, key: String): Long {
        return getLong(name, key, 0L)
    }

    fun getLong(name: String, key: String, default: Long): Long {
        return getSPObject(name).getLong(key, default)
    }

    fun putBoolean(name: String, key: String, value: Boolean) {
        getSPObject(name).edit().putBoolean(key, value).apply()
    }

    fun getBoolean(name: String, key: String): Boolean {
        return getBoolean(name, key, false)
    }

    fun getBoolean(name: String, key: String, default: Boolean): Boolean {
        return getSPObject(name).getBoolean(key, default)
    }

    private fun getSPObject(name: String) :SharedPreferences {
        return KotlinManualApplication.getInstance().applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}