package com.greemoid.githubmessanger.presentation.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class CustomImageView : AppCompatImageView, AbstractView.Image {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr)

    override fun load(url: String) {
        if (url.isNotEmpty())
            Glide.with(this)
                .load(url)
                .into(this)
    }

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}