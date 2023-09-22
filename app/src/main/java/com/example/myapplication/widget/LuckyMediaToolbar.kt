package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

class LuckyMediaToolbar: Toolbar {
    constructor(context: Context) :super(context) {
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet?) :super(context,attributeSet) {
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) :super(context,attributeSet,defStyleAttr) {
        init()
    }

    private fun init() {
//        setContentInsetStartWithNavigation()
    }


}