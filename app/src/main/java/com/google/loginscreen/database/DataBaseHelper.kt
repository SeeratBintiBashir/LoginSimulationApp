package com.google.loginscreen.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.loginscreen.User

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        openDatabase()
    }
    companion object{
        private const val DATABASE_NAME = "UserDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "users"
        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val tableCreateQuery = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_EMAIL TEXT, $KEY_PASSWORD PASSWORD)"
        db?.execSQL(tableCreateQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    private fun openDatabase(){

        val db = writableDatabase
    }

    fun addUser(user: User){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_EMAIL, user.email)
            put(KEY_USERNAME, user.username)
            put(KEY_PASSWORD, user.password)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $KEY_EMAIL = '$email' AND $KEY_PASSWORD = '$password'"
        val cursor: Cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        cursor.close()

        if (cursor.count >0){
            cursor.close()
            db.close()
            return true
        }
            return false
    }

    fun checkEmailExists(email: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $KEY_EMAIL = '$email'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return true
        }
        return false
    }

    }