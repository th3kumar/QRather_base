package com.example.qrather.login_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.qrather.R
import com.example.qrather.main.LoginAcitivity
import com.example.qrather.main.SignupAcitivity

class WelcomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)



        val login_button = findViewById<Button>(R.id.login_button)
        val signup_button = findViewById<Button>(R.id.signup_button)

        login_button.setOnClickListener {

            val intent = Intent(this,LoginAcitivity::class.java)
            startActivity(intent)
        }

        signup_button.setOnClickListener {
            val intent = Intent(this, SignupAcitivity::class.java)
            startActivity(intent)
        }
    }





}