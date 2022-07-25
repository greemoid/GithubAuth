package com.greemoid.githubmessanger.presentation.main

import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.presentation.core.BaseViewModel
import com.greemoid.githubmessanger.presentation.profile.MyProfileFragment
import com.greemoid.githubmessanger.presentation.search.SearchFragment

class MainViewModel(
    communication: NavigationCommunication,
) : BaseViewModel<NavigationCommunication, NavigationUi>(communication) {

    fun changeScreen(menuItemId: Int) {
        //todo save screen id
        communication.map(NavigationUi(menuItemId))
    }

    private val idMap = mapOf(
        R.id.navigation_profile to MyProfileFragment::class.java,
        R.id.navigation_search to SearchFragment::class.java
    )//todo move to some class

    fun getFragment(id: Int): BaseFragment<*> {
        val clazz = idMap[id] ?: throw IllegalStateException("unknown id $id")
        return clazz.newInstance()
    }

    fun init() {
        changeScreen(R.id.navigation_profile) //todo get from navigation cache
    }
}