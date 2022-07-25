package com.greemoid.githubmessanger.presentation.profile

import androidx.lifecycle.viewModelScope
import com.greemoid.githubmessanger.data.profile.MyProfileData
import com.greemoid.githubmessanger.data.profile.MyProfileRepository
import com.greemoid.githubmessanger.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MyProfileViewModel(
    communication: MyProfileCommunication,
    private val repository: MyProfileRepository,
    private val mapper: MyProfileData.MyProfileMapper<MyProfileUi>,
) : BaseViewModel<MyProfileCommunication, MyProfileUi>(communication) {

    fun init() = viewModelScope.launch {
        val data = repository.profile().map(mapper)
        communication.map(data)
    }

    fun signOut() = repository.signOut()
}