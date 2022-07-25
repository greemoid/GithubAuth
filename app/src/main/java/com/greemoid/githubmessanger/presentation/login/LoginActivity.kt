package com.greemoid.githubmessanger.presentation.login

import android.content.Intent
import android.os.Bundle
import com.greemoid.githubmessanger.databinding.ActivityLoginBinding
import com.greemoid.githubmessanger.presentation.core.BaseActivity
import com.greemoid.githubmessanger.presentation.main.MainActivity


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(LoginViewModel::class.java, this)
        viewModel.observe(this) {
            if (it is LoginUi.Success) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                it.map(binding.errorTextView, binding.progressBar, binding.loginButton)
            }
        }
        binding.loginButton.setOnClickListener { viewModel.login(LoginEngine.Login(this)) }
        viewModel.init(LoginEngine.SignIn(this))
    }
}