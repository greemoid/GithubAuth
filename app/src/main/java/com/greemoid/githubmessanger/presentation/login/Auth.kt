package com.greemoid.githubmessanger.presentation.login

import com.greemoid.githubmessanger.data.login.UserInitial

interface Auth {

    fun <T> map(mapper: AuthResultMapper<T>): T

    data class Base(private val profile: Map<String, Any>) : Auth {
        override fun <T> map(mapper: AuthResultMapper<T>): T = mapper.map(profile)
    }

    data class Fail(val e: Exception) : Auth {
        override fun <T> map(mapper: AuthResultMapper<T>): T = mapper.map(emptyMap())
    }

    interface AuthResultMapper<T> {
        fun map(profile: Map<String, Any>): T

        class Base : AuthResultMapper<UserInitial> {
            override fun map(profile: Map<String, Any>): UserInitial = UserInitial(
                profile["name"].toString(),
                profile["login"].toString(),
                profile["email"].toString(),
                profile["bio"].toString(),
                profile["avatar_url"].toString(),
            )
        }
    }
}