package com.dogactanriverdi.movieapp.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}