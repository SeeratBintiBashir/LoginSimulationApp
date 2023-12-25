package com.google.loginscreen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.loginscreen.database.DataBaseHelper
import com.google.loginscreen.database.pref.MySharedPreferences
import com.google.loginscreen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: DataBaseHelper
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var  mySharedPreferences: MySharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mySharedPreferences=MySharedPreferences(this)



        db = DataBaseHelper(this)

        binding.backFromLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.registerFirst.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }



        binding.loginButton.setOnClickListener {
            val email = binding.loginMail.text.toString()
            val password = binding.loginPassword.text.toString()

            val user = db.getUser(email,password)

            if (user) {
                saveUser()
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUser() {
        val password=binding.loginPassword.text.toString()
        val email=binding.loginMail.text.toString()
        mySharedPreferences.saveUser(email,password)
    }
}