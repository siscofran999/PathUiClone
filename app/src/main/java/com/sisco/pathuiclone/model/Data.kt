package com.sisco.pathuiclone.model

import android.graphics.drawable.Drawable

data class Data(
    val profile: Drawable?,
    val anotherProfile: Drawable?,
    val message: String,
    val like: Int
)