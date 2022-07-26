package com.greemoid.githubmessanger.presentation.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.greemoid.githubmessanger.presentation.login.LoginActivity
import com.greemoid.githubmessanger.presentation.main.MainActivity
import com.greemoid.githubmessanger.sl.core.GMApp

abstract class BaseActivity : AppCompatActivity() {

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as GMApp).viewModel(model, owner)

    fun switchToLogin() = switchTo(LoginActivity::class.java)
    fun switchToMain() = switchTo(MainActivity::class.java)

    private fun switchTo(clasz: Class<*>) {
        startActivity(Intent(this, clasz))
        finish()
    }
}