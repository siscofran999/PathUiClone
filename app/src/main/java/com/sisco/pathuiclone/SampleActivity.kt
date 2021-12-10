package com.sisco.pathuiclone

import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class SampleActivity : AppCompatActivity() , AppBarLayout.OnOffsetChangedListener {

    private var collapsedScale = 0f
    private var expandedScale = 0f
    private lateinit var photoView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        photoView = findViewById(R.id.photo_image)

        val mAppBarLayout = findViewById<View>(R.id.appbar) as AppBarLayout
        mAppBarLayout.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val maxScroll = appBarLayout!!.totalScrollRange

        val scrollPercent = abs(verticalOffset).toFloat() / maxScroll.toFloat()
        Log.i("TAG", "scalePhotoImage onOffsetChanged: $collapsedScale")
        if (collapsedScale == 0f) {
            val photo: Drawable = photoView.drawable
            val bitmapWidth = photo.intrinsicWidth
            val bitmapHeight = photo.intrinsicHeight
            collapsedScale = photoView.width.toFloat() / bitmapWidth.toFloat()
            expandedScale = photoView.height.toFloat() / bitmapHeight.toFloat()
            scalePhotoImage(photoView, expandedScale)
        } else {
            scalePhotoImage(
                photoView,
                collapsedScale + (expandedScale - collapsedScale) * (1f - scrollPercent)
            )
        }
    }

    private fun scalePhotoImage(photoView: ImageView, scale: Float) {
        val photo = photoView.drawable
        val bitmapWidth = photo.intrinsicWidth
        val bitmapHeight = photo.intrinsicHeight
        val offsetX = (photoView.width - bitmapWidth) / 2f
        val offsetY = (photoView.height - bitmapHeight) / 2f
        val centerX = photoView.width / 2f
        val centerY = photoView.height / 2f
        val imageMatrix = Matrix()
        imageMatrix.setScale(scale, scale, centerX, centerY)
        imageMatrix.preTranslate(offsetX, offsetY)
        Log.i("TAG", "scalePhotoImage: scale -> $scale")
        Log.i("TAG", "scalePhotoImage: centerX -> $centerX")
        Log.i("TAG", "scalePhotoImage: centerY -> $centerY")
        Log.i("TAG", "scalePhotoImage: offsetX -> $offsetX")
        Log.i("TAG", "scalePhotoImage: offsetY -> $offsetY")
        photoView.imageMatrix = imageMatrix
    }
}