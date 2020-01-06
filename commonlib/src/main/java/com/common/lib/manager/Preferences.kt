package com.common.lib.manager

import android.content.SharedPreferences

class Preferences private constructor() {

    private val TAG = "Preferences"
    private var mSetting: SharedPreferences? = null

    init {
        mSetting =
            ConfigurationManager.getInstance().getContext()?.getSharedPreferences("Confug", 0)
    }

    companion object {
        @Volatile
        private var instance: Preferences? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Preferences().also { instance = it }
            }
    }

    fun getSettings(): SharedPreferences? {
        if (mSetting == null) {
            mSetting =
                ConfigurationManager.getInstance().getContext()?.getSharedPreferences("Confug", 0)
        }
        return mSetting
    }

    fun setValues(key: String?, value: String?) {
        val editor: SharedPreferences.Editor = getSettings()!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun setValues(key: String?, value: Boolean) {
        val editor: SharedPreferences.Editor = getSettings()!!.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun setValues(key: String?, value: Long) {
        val editor: SharedPreferences.Editor = getSettings()!!.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun setValues(key: String?, value: Int) {
        val editor: SharedPreferences.Editor = getSettings()!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getValues(key: String?, value: String?): String? {
        return getSettings()!!.getString(key, value)
    }

    fun getValues(key: String?, defValue: Boolean): Boolean {
        return getSettings()!!.getBoolean(key, defValue)
    }

    fun getValues(key: String?, defValue: Int): Int {
        return getSettings()!!.getInt(key, defValue)
    }

    fun getValues(key: String?, defValue: Long): Long {
        return getSettings()!!.getLong(key, defValue)
    }

    fun getStringByKey(key: String?): String? {
        return getValues(key, "")
    }

    fun getBoolByKey(key: String?): Boolean {
        return getValues(key, false)
    }

    fun getLongByKey(key: String?): Long {
        return getValues(key, 0L)
    }

    fun getIntByKey(key: String?): Int? {
        return getValues(key, 0)
    }
}