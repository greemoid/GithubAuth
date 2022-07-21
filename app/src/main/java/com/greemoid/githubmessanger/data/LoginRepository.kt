package com.greemoid.githubmessanger.data

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greemoid.githubmessanger.core.Save

interface LoginRepository : Save<String> {

    fun user(): Any?

    class Base(private val sharedPreferences: SharedPreferences) : LoginRepository {
        override fun user() = Firebase.auth.currentUser

        override fun save(data: String) =
            sharedPreferences.edit().putString("profile", data).apply()

    }
}