package com.greemoid.githubmessanger.presentation.main

import com.greemoid.githubmessanger.presentation.core.Communication


interface NavigationCommunication : Communication<NavigationUi> {
    class Base : Communication.Base<NavigationUi>(), NavigationCommunication
}