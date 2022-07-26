package com.greemoid.githubmessanger.data.login

import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greemoid.githubmessanger.core.FirebaseDatabaseProvider
import com.greemoid.githubmessanger.core.Save
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface LoginRepository {

    suspend fun saveUser(user: UserInitial)

    fun user(): Any?

    class Base(private val firebaseDatabaseProvider: FirebaseDatabaseProvider) : LoginRepository {
        override suspend fun saveUser(user: UserInitial) {
            val value = firebaseDatabaseProvider.provideDatabase()
                .child("users")
                .child(user()!!.uid)
                .setValue(user)
            handleResult(value)
        }

        private suspend fun handleResult(value: Task<Void>): Unit =
            suspendCoroutine { continuation ->
                value.addOnSuccessListener {
                    continuation.resume(Unit)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
            }

        override fun user() = Firebase.auth.currentUser



    }
}