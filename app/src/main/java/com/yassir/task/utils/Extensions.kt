package com.yassir.task.utils

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.yassir.task.R


fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(path: String) {
    Picasso.get().load(Constants.IMAGE_BASE_URL + path).fit()
        .centerInside().placeholder(R.drawable.ic_launcher_background)
        .into(this)
}