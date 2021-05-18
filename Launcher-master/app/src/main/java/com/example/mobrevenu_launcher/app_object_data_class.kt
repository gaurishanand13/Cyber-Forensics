package com.example.mobrevenu_launcher

import android.graphics.drawable.Drawable

data class appObject(
    var name : String,
    var image : Drawable,
    var packageName : String,
    var system_app_or_not : Boolean
)