package com.greemoid.githubmessanger.data.profile

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.greemoid.githubmessanger.core.FirebaseDatabaseProvider
import com.greemoid.githubmessanger.data.login.UserInitial
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface MyProfileRepository {

    suspend fun profile() : MyProfileData

    fun signOut()

    class Base(private val firebaseDatabaseProvider: FirebaseDatabaseProvider) :
            MyProfileRepository {
        override suspend fun profile(): MyProfileData {
            val user = firebaseDatabaseProvider.provideDatabase()
                .child("users")
                .child(Firebase.auth.currentUser!!.uid)
            return MyProfileData.Base(handleResult(user))
        }

        private suspend fun handleResult(reference: DatabaseReference) =
            suspendCoroutine<UserInitial> {continuation ->
                reference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        continuation.resume(snapshot.getValue(UserInitial::class.java)!!)
                    }

                    override fun onCancelled(error: DatabaseError) = Unit

                })
            }

        override fun signOut() = Firebase.auth.signOut()

    }
}