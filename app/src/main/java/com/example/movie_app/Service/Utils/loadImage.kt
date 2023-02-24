package com.example.movie_app.Service.Utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view: ImageView, uri:String){

    Glide.with(view).load(uri).into(view)
}
