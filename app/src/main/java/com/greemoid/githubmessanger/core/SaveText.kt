package com.greemoid.githubmessanger.core

class SaveText(private val dataSource: Save<String>) : TextMapper.Void {
    override fun map(data: String) = dataSource.save(data)

}