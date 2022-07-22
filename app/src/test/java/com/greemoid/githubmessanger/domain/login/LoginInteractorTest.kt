package com.greemoid.githubmessanger.domain.login

import com.greemoid.githubmessanger.core.TextMapper
import com.greemoid.githubmessanger.data.LoginRepository
import com.greemoid.githubmessanger.presentation.login.Auth
import com.greemoid.githubmessanger.presentation.login.LoginWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.collections.HashMap
import org.junit.Assert.assertEquals

class LoginInteractorTest {

    private val exception = IllegalAccessException("error")

    @Test
    fun test_success() = runBlocking {
        val repository = TestRepository()
        val interactor = LoginInteractor.Base(repository)
        val actual = interactor.login(TestLoginWrapper(true))
        val expected = Auth.Base(HashMap<String, Any>().apply {
            put("bio", "test bio")
        })
        assertEquals(expected, actual)
        val expectedString = "test bio"
        assertEquals(expectedString, repository.saved)
    }

    @Test
    fun test_fail() = runBlocking {
        val repository = TestRepository()
        val interactor = LoginInteractor.Base(repository)
        val actual = interactor.login(TestLoginWrapper(false))
        val expected = Auth.Fail(exception)
        assertEquals(expected, actual)
        val expectedString = expected.map(TestTextMapper())
        val actualString = actual.map(TestTextMapper())
        assertEquals(expectedString, actualString)
    }

    @Test
    fun test_authorized() {
        val repository = TestRepository(true)
        val interactor = LoginInteractor.Base(repository)
        val actual = interactor.authorized()
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_authorized() {
        val repository = TestRepository(false)
        val interactor = LoginInteractor.Base(repository)
        val actual = interactor.authorized()
        val expected = false
        assertEquals(expected, actual)
    }

    private inner class TestLoginWrapper(private val success: Boolean) : LoginWrapper {
        override suspend fun login(): Auth =
            if (success) Auth.Base(HashMap<String, Any>().apply {
                put("bio", "test bio")
            }) else
                Auth.Fail(exception)

    }

    private inner class TestTextMapper : TextMapper<String> {
        override fun map(data: String): String {
            return data
        }

    }

    private inner class TestRepository(private val authorized: Boolean = false) : LoginRepository {

        var saved = ""

        override fun user(): Any? = if (authorized) Object() else null

        override fun save(data: String) {
            saved = data
        }

    }

}