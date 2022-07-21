package com.greemoid.githubmessanger.presentation.core

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.greemoid.githubmessanger.sl.core.GMApp

abstract class BaseActivity : AppCompatActivity() {

    protected fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as GMApp).viewModel(model, owner)
}