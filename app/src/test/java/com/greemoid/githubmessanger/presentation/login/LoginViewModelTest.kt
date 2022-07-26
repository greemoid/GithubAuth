package com.greemoid.githubmessanger.presentation.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.greemoid.githubmessanger.domain.login.LoginInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class LoginViewModelTest {

    @ExperimentalCoroutinesApi
    @Test
    fun test_access() = runBlocking {
        val communication = TestCommunication()
        val dispatcher = TestCoroutineDispatcher()
        val viewModel = LoginViewModel(communication, TestInteractor(), dispatcher, dispatcher)
        viewModel.login(TestLoginEngine(true))
        val actual = communication.loginUi
        val expected = LoginUi.Success
        assertEquals(expected, actual)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_fail() = runBlocking {
        val communication = TestCommunication()
        val dispatcher = TestCoroutineDispatcher()
        val viewModel = LoginViewModel(communication, TestInteractor(), dispatcher, dispatcher)
        viewModel.login(TestLoginEngine(false))
        val actual = communication.loginUi
        val expected = LoginUi.Failed("error")
        assertEquals(expected, actual)
    }


    private inner class TestCommunication : LoginCommunication {

        var loginUi: LoginUi = LoginUi.Progress()

        override fun observe(owner: LifecycleOwner, observer: Observer<LoginUi>) = Unit

        override fun map(data: LoginUi) {
            loginUi = data
        }

    }

    private inner class TestInteractor : LoginInteractor {
        override fun authorized(): Boolean = false

        override suspend fun login(loginEngine: LoginEngine): Auth {
            return loginEngine.login()
        }

    }

    private inner class TestLoginEngine(private val success: Boolean) : LoginEngine {
        override suspend fun login(): Auth =
            if (success) Auth.Base(emptyMap()) else Auth.Fail(IllegalStateException("error"))

    }

}