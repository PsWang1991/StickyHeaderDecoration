package com.example.stickyheadertest.ui.main

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by PS Wang on 2023/1/12
 */

val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp: Float
    get() = this.toFloat().dp