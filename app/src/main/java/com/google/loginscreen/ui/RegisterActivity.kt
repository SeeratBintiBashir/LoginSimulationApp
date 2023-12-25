package com.google.loginscreen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.loginscreen.database.DataBaseHelper
import com.google.loginscreen.database.models.User
import com.google.loginscreen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = DataBaseHelper(this)

        binding.backFromRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.loginAlreadyRegistered.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {

            val email = binding.registerEmail.text.toString()
            val username = binding.registerUsername.text.toString()
            val password = binding.registerPassword.text.toString()

            val user = db.checkEmailExists(email)


            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (user) {
                    Toast.makeText(this, "Already Registered, Please Login", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val user = User(0, email, username, password)
                    db.addUser(user)
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()

            }
        }
    }
}