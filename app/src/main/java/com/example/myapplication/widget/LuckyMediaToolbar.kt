package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.utils.dp

class LuckyMediaToolbar: Toolbar {
    var reflected = false

    constructor(context: Context) :super(context) {}
    constructor(context: Context,attributeSet: AttributeSet?) :super(context,attributeSet) {}
    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) :super(context,attributeSet,defStyleAttr) {}

    init {
        contentInsetStartWithNavigation = 56.dp
    }




}