package com.greemoid.githubmessanger.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ValueEventListener

interface FirebaseDatabaseProvider {

    fun provideDatabase(): DatabaseReference

    class Base : FirebaseDatabaseProvider {

        init {
            Firebase.database.setPersistenceEnabled(false)

            provideDatabase().run {
                addValueEventListener(EmptyDataListener())
                child("users")
                    .addValueEventListener(EmptyDataListener())
            }
        }

        override fun provideDatabase(): DatabaseReference {
            return Firebase.database.reference.root
        }

        class EmptyDataListener : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) = Unit

            override fun onCancelled(error: DatabaseError) = Unit

        }

    }
}