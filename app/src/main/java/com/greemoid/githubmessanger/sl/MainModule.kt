package com.greemoid.githubmessanger.sl

import com.greemoid.githubmessanger.presentation.main.MainViewModel
import com.greemoid.githubmessanger.presentation.main.NavigationCommunication
import com.greemoid.githubmessanger.sl.core.BaseModule

class MainModule : BaseModule<MainViewModel> {
    override fun viewModel() = MainViewModel(
        NavigationCommunication.Base()
    )


}