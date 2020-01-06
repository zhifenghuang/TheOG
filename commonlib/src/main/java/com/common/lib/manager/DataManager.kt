package com.common.lib.manager

class DataManager private constructor() {

    private val TAG = "DataManager"

    init {

    }

    companion object {
        @Volatile
        private var instance: DataManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataManager().also { instance = it }
            }
    }
}