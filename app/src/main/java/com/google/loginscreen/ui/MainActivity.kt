package com.google.loginscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.loginscreen.database.DataBaseHelper
import com.google.loginscreen.database.pref.MySharedPreferences
import com.google.loginscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: MySharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences= MySharedPreferences(this)


        if(sharedPreferences.isUserLoggedIn()) {
            val db = DataBaseHelper(this)
            val email=sharedPreferences.getString(MySharedPreferences.KEY_EMAIL)
            val password=sharedPreferences.getString(MySharedPreferences.KEY_PASSWORD)
            val user = db.getUser(email,password)
            if (user) {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            }
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