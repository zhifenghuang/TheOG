package com.yeemos.theog.bean

import java.io.Serializable

class ItemBean(id: Int, text: String?) : Serializable {

    var text: String? = null
    var id: Int = 0
    var isSelected: Boolean = false

    init {
        this.text = text;
        this.id = id
    }

}