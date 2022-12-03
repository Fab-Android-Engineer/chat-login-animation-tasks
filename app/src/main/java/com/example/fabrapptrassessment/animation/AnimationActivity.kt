package com.example.fabrapptrassessment.animation


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrapptrassessment.R


class AnimationActivity : AppCompatActivity() {
    var xDown : Double? = 0.0
    var yDown : Double? = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        // set action bar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // display the back -> button
        supportActionBar?.setTitle(R.string.animation_status) // update status title

        initView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        val imgTech = findViewById<ImageView>(R.id.d_a_tech_id)
        val fadeBtn = findViewById<Button>(R.id.fade_in_out_id)
        // wire up media player
        val rocketSound = MediaPlayer.create(this, R.raw.rocketwhoosh)

        // fade in and out and play sound
        fadeBtn.setOnClickListener {
            val animFadeInOut = AnimationUtils.loadAnimation(this,R.anim.fade_in_out)
            imgTech.startAnimation(animFadeInOut)
                rocketSound.start()
        }
        // touch and drag
       imgTech.setOnTouchListener{v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    xDown = v.x.toDouble() - event.rawX
                    yDown = v.y.toDouble() - event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // animate the image on x axis line only according to your finger movements
                    imgTech.animate()
                        .x(event.rawX + xDown!!.toFloat())
                        .y(event.rawY + yDown!!.toFloat())
                        .setDuration(0)
                        .start()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    companion object {
        fun startIntent(context: Context) : Intent {
            return Intent(context, AnimationActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}