package com.greemoid.githubmessanger.sl.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory

class GMApp : Application() {

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule))
    }

    private lateinit var coreModule: CoreModule

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )
        coreModule = CoreModule.Base(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}