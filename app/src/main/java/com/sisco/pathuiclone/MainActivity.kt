package com.sisco.pathuiclone

import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img = findViewById<AppCompatImageView>(R.id.imgBgProfile)

//        val matrix = Matrix()
//        matrix.postRotate(45f, 20f, 20f)
//        ImageView.setImageMatrix(matrix)
//        matrix.set(img.imageMatrix)
//        img.imageMatrix = matrix
    }
}