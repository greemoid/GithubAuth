package com.greemoid.githubmessanger.domain.login

import com.greemoid.githubmessanger.core.SaveText
import com.greemoid.githubmessanger.data.login.LoginRepository
import com.greemoid.githubmessanger.data.login.UserInitial
import com.greemoid.githubmessanger.presentation.login.Auth
import com.greemoid.githubmessanger.presentation.login.LoginEngine

interface LoginInteractor {

    fun authorized(): Boolean

    suspend fun login(loginEngine: LoginEngine): Auth
    suspend fun signIn(signIn: LoginEngine): Auth

    class Base(
        private val repository: LoginRepository,
        private val mapper: Auth.AuthResultMapper<UserInitial>,
    ) : LoginInteractor {
        override fun authorized() = repository.user() != null

        override suspend fun login(loginEngine: LoginEngine): Auth = try {
            val result = loginEngine.login()
            repository.saveUser(result.map(mapper))
            result
        } catch (e: Exception) {
            Auth.Fail(e)
        }

        override suspend fun signIn(signIn: LoginEngine): Auth = try {
            signIn.login()
        } catch (e: Exception) {
            Auth.Fail(e)
        }
    }
}