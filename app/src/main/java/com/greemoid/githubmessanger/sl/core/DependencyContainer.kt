package com.greemoid.githubmessanger.sl.core

import com.greemoid.githubmessanger.sl.login.LoginModule


interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule): DependencyContainer {

        override fun module(feature: Feature): BaseModule<*> = when(feature) {
            Feature.LOGIN -> LoginModule(coreModule)
            else -> throw IllegalArgumentException("unknown feature $feature")
        }


    }
}