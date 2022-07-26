package com.greemoid.githubmessanger.sl

import com.greemoid.githubmessanger.data.login.LoginRepository
import com.greemoid.githubmessanger.domain.login.LoginInteractor
import com.greemoid.githubmessanger.presentation.login.Auth
import com.greemoid.githubmessanger.presentation.login.LoginCommunication
import com.greemoid.githubmessanger.presentation.login.LoginViewModel
import com.greemoid.githubmessanger.sl.core.BaseModule
import com.greemoid.githubmessanger.sl.core.CoreModule

class LoginModule(private val coreModule: CoreModule) : BaseModule<LoginViewModel>{
    override fun viewModel(): LoginViewModel =
        LoginViewModel(
            LoginCommunication.Base(),
            LoginInteractor.Base(
                LoginRepository.Base(coreModule.firebaseDatabaseProvider()),
                Auth.AuthResultMapper.Base()
            )
        )
}