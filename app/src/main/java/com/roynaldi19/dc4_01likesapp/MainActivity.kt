package com.roynaldi19.dc4_01likesapp

import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.roynaldi19.dc4_01likesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(bitmap)
    private val paint = Paint()

    private val halfOfWidth = (bitmap.width/2).toFloat()
    private val halfOfHeight = (bitmap.height/2).toFloat()

    private val left = 150F
    private val top = 250F
    private val right = bitmap.width - left
    private val bottom = bitmap.height.toFloat() - 50F

    private val message = "Apakah kamu suka bermain?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageBitmap(bitmap)
        showText()

        binding.like.setOnClickListener {
            showEars()
            showFace()
            showEyes()
            showMouth(true)
            showNose()
            showHair()
        }

        binding.dislike.setOnClickListener {
            showEars()
            showFace()
            showEyes()
            showMouth(false)
            showNose()
            showHair()
        }

    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)

        paint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        canvas.drawArc(face, 90F, 180F, false, paint)

        paint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        canvas.drawArc(face, 270F, 180F, false, paint)

    }

    private fun showEyes() {
        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 10F, 50F, paint)
        canvas.drawCircle(halfOfWidth + 100F, halfOfHeight - 10F, 50F, paint)

        paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        canvas.drawCircle(halfOfWidth - 120F, halfOfHeight - 20F, 15F, paint)
        canvas.drawCircle(halfOfWidth + 80F, halfOfHeight - 20F, 15F, paint)
    }

    private fun showMouth(isHappy: Boolean) {
        when (isHappy) {
            true -> {
                paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight - 100F, halfOfWidth + 200F, halfOfHeight + 400F)
                canvas.drawArc(lip, 25F, 130F, false, paint)

                paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight, halfOfWidth + 180F, halfOfHeight + 380F)
                canvas.drawArc(mouth, 25F, 130F, false, paint)

            }
            false -> {
                paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight + 250F, halfOfWidth + 200F, halfOfHeight + 350F)
                canvas.drawArc(lip, 0F, -180F, false, paint)

                paint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight + 260F, halfOfWidth + 180F, halfOfHeight + 330F)
                canvas.drawArc(mouth, 0F, -180F, false, paint)
            }
        }
    }

    private fun showText() {
        val paintText =  Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50F
            color = ResourcesCompat.getColor(resources, R.color.black , null)
        }

        val mBounds = Rect()
        paintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halfOfWidth - mBounds.centerX()
        val y = 50F
        canvas.drawText(message, x, y, paintText)
    }

    private fun showNose() {
        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 40F, halfOfHeight + 140F, 15F, paint)
        canvas.drawCircle(halfOfWidth + 40F, halfOfHeight + 140F, 15F, paint)
    }

    private fun showEars() {
        paint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 100F, paint)

        paint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 100F, paint)

        paint.color = ResourcesCompat.getColor(resources, R.color.red_ear, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 60F, paint)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 60F, paint)
    }

    private fun showHair() {
        canvas.save()

        val path = Path()

        path.addCircle(halfOfWidth - 100F,halfOfHeight - 10F, 150F, Path.Direction.CCW)
        path.addCircle(halfOfWidth + 100F,halfOfHeight - 10F, 150F, Path.Direction.CCW)

        val mouth = RectF(halfOfWidth - 250F, halfOfHeight, halfOfWidth + 250F, halfOfHeight + 500F)
        path.addOval(mouth, Path.Direction.CCW)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }

        val face = RectF(left, top, right, bottom)

        paint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawArc(face, 90F, 180F, false, paint)

        paint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawArc(face, 270F, 180F, false, paint)

        canvas.restore()
    }
}