package com.greemoid.githubmessanger.presentation.search

import androidx.appcompat.widget.SearchView


class SimpleQueryListener(private val search: Search) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean = find(query)

    override fun onQueryTextChange(newText: String?): Boolean = find(newText)

    private fun find(query: String?): Boolean {
        search.search(query.orEmpty().trim())
        return !query.isNullOrEmpty()
    }
}