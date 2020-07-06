package com.example.demoapp.Persistence

import android.content.Context
import android.content.SharedPreferences

class mSharedPreferences {
    var context : Context
    var sharedPreferences : SharedPreferences
    val editor : SharedPreferences.Editor

    constructor(context: Context) {
        this.context = context
        this.sharedPreferences = context.getSharedPreferences("KOTLIN_USUARIO", Context.MODE_PRIVATE)
        this.editor = this.sharedPreferences.edit()
    }
    fun put(key:String, value : String) {
        this.editor.putString(key, value)
    }
    fun save() {
        this.editor.apply()
    }

    fun getKey(key: String) : String? {
        return this.sharedPreferences.getString(key, "none")
    }
}