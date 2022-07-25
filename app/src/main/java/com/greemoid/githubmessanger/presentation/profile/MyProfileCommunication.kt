package com.greemoid.githubmessanger.presentation.profile

import com.greemoid.githubmessanger.presentation.core.Communication

interface MyProfileCommunication : Communication<MyProfileUi> {

    class Base : Communication.Base<MyProfileUi>(), MyProfileCommunication
}