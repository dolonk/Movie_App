package com.example.movie_app.View.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}