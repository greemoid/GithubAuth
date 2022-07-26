package com.greemoid.githubmessanger.core

interface Match<T> {

    fun matches(data: T): Boolean
}