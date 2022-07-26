package com.greemoid.githubmessanger.sl.core

import com.greemoid.githubmessanger.sl.LoginModule
import com.greemoid.githubmessanger.sl.MainModule
import com.greemoid.githubmessanger.sl.MyProfileModule
import com.greemoid.githubmessanger.sl.SearchModule


interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule): DependencyContainer {

        override fun module(feature: Feature): BaseModule<*> = when(feature) {
            Feature.LOGIN -> LoginModule(coreModule)
            Feature.MAIN -> MainModule()
            Feature.SEARCH -> SearchModule(coreModule)
            Feature.MY_PROFILE -> MyProfileModule(coreModule)
            else -> throw IllegalArgumentException("unknown feature $feature")
        }


    }
}