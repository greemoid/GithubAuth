package com.greemoid.githubmessanger.sl.core

import android.content.Context
import android.content.SharedPreferences
import com.greemoid.githubmessanger.core.FirebaseDatabaseProvider

interface CoreModule {

    fun provideSharedPreferences() : SharedPreferences
    fun firebaseDatabaseProvider() : FirebaseDatabaseProvider

    class Base(private val context: Context): CoreModule {

        private val firebaseDatabaseProvider = FirebaseDatabaseProvider.Base()

        override fun provideSharedPreferences(): SharedPreferences =
            context.getSharedPreferences("GMSharedPref", Context.MODE_PRIVATE)

        override fun firebaseDatabaseProvider(): FirebaseDatabaseProvider =
            firebaseDatabaseProvider
    }
}