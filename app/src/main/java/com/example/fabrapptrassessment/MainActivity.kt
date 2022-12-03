package com.example.fabrapptrassessment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fabrapptrassessment.animation.AnimationActivity
import com.example.fabrapptrassessment.chat.view.ChatActivity
import com.example.fabrapptrassessment.login.CustomDialogFragment
import com.example.fabrapptrassessment.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // set action bar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setTitle(R.string.activity_main_title) // update status title
        // init
        initView()

    }
    // loads views
    private fun initView() {
        val chatBtn = findViewById<Button>(R.id.chat_btn)
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val animateBtn = findViewById<Button>(R.id.animation_btn)
        // calling onLoginClicked
        onChatClicked(chatBtn)
        // calling onLoginClicked
        onLoginClicked(loginBtn)
        // calling onLoginClicked
        onAnimationClicked(animateBtn)

    }

    private fun onChatClicked(btn : Button) {
        btn.setOnClickListener {
            startActivity(ChatActivity.startIntent(this))
        }
    }
    private fun onLoginClicked(btn : Button) {
        btn.setOnClickListener {
            startActivity(LoginActivity.startIntent(this))
        }
    }
    private fun onAnimationClicked(btn : Button) {
        btn.setOnClickListener {
            startActivity(AnimationActivity.startIntent(this))
        }
    }

    // object
    companion object {
        fun startIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}