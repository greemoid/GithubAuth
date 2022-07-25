package com.greemoid.githubmessanger.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.greemoid.githubmessanger.core.Abstract

abstract class BaseViewModel<E : Communication<T>, T : Abstract.UiObject>(protected val communication: E) :
    ViewModel(), Observe<T> {

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        communication.observe(owner, observer)
    }
}