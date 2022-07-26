package com.greemoid.githubmessanger.sl

import com.greemoid.githubmessanger.data.profile.MyProfileData
import com.greemoid.githubmessanger.data.profile.MyProfileRepository
import com.greemoid.githubmessanger.presentation.profile.MyProfileCommunication
import com.greemoid.githubmessanger.presentation.profile.MyProfileViewModel
import com.greemoid.githubmessanger.sl.core.BaseModule
import com.greemoid.githubmessanger.sl.core.CoreModule

class MyProfileModule(private val coreModule: CoreModule) : BaseModule<MyProfileViewModel> {

    override fun viewModel(): MyProfileViewModel = MyProfileViewModel(
        MyProfileCommunication.Base(),
        MyProfileRepository.Base(coreModule.firebaseDatabaseProvider()),
        MyProfileData.MyProfileMapper.Base()
    )


}