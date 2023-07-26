package com.example.bisdiv_burgerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PromptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)


        val user = findViewById<Button>(R.id.btn_user)
        user.setOnClickListener {
            val Intent = Intent(this, SignupActivity::class.java)
            startActivity(Intent)

            val Rider = findViewById<Button>(R.id.btn_rider)
            Rider.setOnClickListener {
                val Intent = Intent(this, riderLoginActivity::class.java)
                startActivity(Intent)
            }

        }

    }
}