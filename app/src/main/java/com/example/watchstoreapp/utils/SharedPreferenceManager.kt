package com.example.watchstoreapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.watchstoreapp.model.User


class SharedPreferenceManager(context: Context) {

    var sharedpreferences: SharedPreferences =  context.getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE)

    fun addUserData(user: User){
        try {
            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.putString("userName", user.name)
            editor.putString("userMobile", user.mobile)

            editor.commit()
        }catch (e:Exception){
            Log.i("Error", e.printStackTrace().toString())
        }
    }



    fun getUserMobile(): String{
        return sharedpreferences.getString("userMobile","")!!
    }

    fun getUserData():Array<String>{
        val name = sharedpreferences.getString("userName","")!!
        val mobile = sharedpreferences.getString("userMobile","")!!
        return arrayOf(name,  mobile)
    }
}