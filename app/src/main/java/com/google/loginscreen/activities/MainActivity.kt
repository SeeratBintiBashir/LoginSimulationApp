package com.google.loginscreen.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.loginscreen.database.DataBaseHelper
import com.google.loginscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("MY_PREFs", MODE_PRIVATE)

        val loggedInEmail = sharedPreferences.getString("email","")
        val loggedInPassword = sharedPreferences.getString("password","")

         val db = DataBaseHelper(this)


        val user = db.getUser(loggedInEmail!!,loggedInPassword!!)
        if (user) {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        binding.continueWithMail.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginRedirectText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}