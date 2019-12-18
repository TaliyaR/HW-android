package com.itis.template

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
        val title: String,
        val artist: String,
        val album: String,
        @DrawableRes val cover: Int,
        @RawRes val file: Int
)
