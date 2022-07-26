package com.greemoid.githubmessanger.data.search

import com.google.firebase.database.*
import com.greemoid.githubmessanger.core.FirebaseDatabaseProvider
import com.greemoid.githubmessanger.data.login.UserInitial
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface SearchUserRepository {

    suspend fun search(query: String): List<SearchData>

    class Base(
        private val databaseProvider: FirebaseDatabaseProvider,
    ) : SearchUserRepository {
        override suspend fun search(query: String): List<SearchData> {
            val users = databaseProvider.provideDatabase()
                .child("users")
                .orderByChild("login")
                .equalTo(query)

            return handleResult(users).map { (key, data) ->
                SearchData.Base(
                    id = key,
                    name = data.name,
                    photoUrl = data.photoUrl
                )
            }
        }

        private suspend fun handleResult(users: Query) =
            suspendCoroutine<List<Pair<String, UserInitial>>> { continuation ->
                users.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.mapNotNull {
                            Pair(it.key!!, it.getValue(UserInitial::class.java)!!)
                        }.let {
                            continuation.resume(it)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) = Unit

                })
            }

    }

}