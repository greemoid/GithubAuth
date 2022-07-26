package com.greemoid.githubmessanger.presentation.search

import java.util.*

class Delay(private val block: (String) -> Unit) {

    private var timer: Timer? = null
    private var isRunning = false
    private var cached: String = ""
    private var time: Long = 0

    fun add(query: String) {
        cached = query
        time = System.currentTimeMillis()
        if (!isRunning)
            start()
    }

    private fun start() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                if (System.currentTimeMillis() - time >= DELAY) {
                    block(cached)
                    clear()
                }
            }
        }, 0, DELAY)
    }

    fun clear() {
        timer?.purge()
        timer?.cancel()
        timer = null
        isRunning = false
    }

    private companion object {
        const val DELAY = 300L
    }
}