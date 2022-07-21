package com.greemoid.githubmessanger.domain.login

import com.greemoid.githubmessanger.core.SaveText
import com.greemoid.githubmessanger.data.LoginRepository
import com.greemoid.githubmessanger.presentation.login.Auth
import com.greemoid.githubmessanger.presentation.login.LoginWrapper

interface LoginInteractor {

    fun authorized(): Boolean

    suspend fun login(loginWrapper: LoginWrapper): Auth

    class Base(private val repository: LoginRepository) : LoginInteractor {
        override fun authorized() = repository.user() != null

        override suspend fun login(loginWrapper: LoginWrapper): Auth {
            val result = loginWrapper.login()
            if (result is Auth.Base)
                result.map(SaveText(repository))
            return result
        }
    }
}