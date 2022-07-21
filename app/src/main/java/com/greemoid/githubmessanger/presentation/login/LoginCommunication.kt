package com.greemoid.githubmessanger.presentation.login

import com.greemoid.githubmessanger.presentation.core.Communication

interface LoginCommunication : Communication<LoginUi>{

    class Base : Communication.Base<LoginUi>(), LoginCommunication

}