package com.google.loginscreen.database.pref

import android.accounts.AccountManager.KEY_PASSWORD
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import com.google.loginscreen.database.pref.MySharedPreferences.Companion.KEY_EMAIL

class    MySharedPreferences(context: Context) {
    val KEY_PREF_NAME="MY_PREFs";
    private val sharedPreferences: SharedPreferences
    private val editor: Editor
   companion object {
       val KEY_EMAIL = "email"
       val KEY_PASSWORD = "password"
   }




    init {
        sharedPreferences= context.getSharedPreferences("MY_PREFs", AppCompatActivity.MODE_PRIVATE)
        editor=sharedPreferences.edit();
    }

     fun saveUser(email:String,password:String) {
        editor.putString(KEY_EMAIL,email)
        editor.putString(KEY_PASSWORD,password)
        editor.apply()
        editor.commit()

    }

    fun isUserLoggedIn():Boolean{
        var isLoggedIn=false;

        val loggedInUserEmail=getString(KEY_EMAIL)
        val loggedInUserPassword=getString(KEY_PASSWORD)
        if(loggedInUserEmail.isNotBlank() && loggedInUserPassword.isNotBlank())
        {
            isLoggedIn=true;
        }

        return  isLoggedIn;


    }

    fun getString(key:String):String
    {
        return sharedPreferences.getString(key,"")?:""

    }


    fun logoutUser()
    {
        saveUser("","")
    }





}