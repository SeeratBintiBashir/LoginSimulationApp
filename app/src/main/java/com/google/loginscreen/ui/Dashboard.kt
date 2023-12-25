package com.google.loginscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.loginscreen.database.pref.MySharedPreferences
import com.google.loginscreen.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    lateinit var  sharedPreferences: MySharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences= MySharedPreferences(this)
          if(sharedPreferences.isUserLoggedIn()==false)
          {
              finish()
          }


//        binding.loginText.text = "Your Email is: ${editor.getString("email", null)}" +
//                "And Your Password is: ${editor.getString("password", null)}"


        binding.logoutButton.setOnClickListener {
             sharedPreferences.logoutUser()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

}

}