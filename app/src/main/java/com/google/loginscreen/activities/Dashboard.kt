package com.google.loginscreen.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.loginscreen.R
import com.google.loginscreen.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val editor = getSharedPreferences("MY_PREFs", MODE_PRIVATE)
        binding.loginText.text = "Your Email is: ${editor.getString("email", null)}" +
                "And Your Password is: ${editor.getString("password", null)}"


        binding.logoutButton.setOnClickListener {
            editor.edit().remove("email").commit()
            editor.edit().remove("password").commit()

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

}

}