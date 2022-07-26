package com.greemoid.githubmessanger.presentation.search

import androidx.lifecycle.viewModelScope
import com.greemoid.githubmessanger.data.search.SearchUserRepository
import com.greemoid.githubmessanger.presentation.core.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    searchCommunication: SearchCommunication,
    private val mapper: SearchResultsMapper,
    private val repository: SearchUserRepository,
    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatchersMain: CoroutineDispatcher = Dispatchers.Main,
) : BaseViewModel<SearchCommunication, SearchUserListUi>(searchCommunication), Search {

    private val delay = Delay { query ->
        viewModelScope.launch(dispatchersIO) { find(query) }
    }

    private val initial = SearchUserListUi.Base(listOf(SearchUserUi.Search()))

    private var cleared = false

    override fun search(query: String) {
        cleared = query.length < 3
        if (cleared) {
            delay.clear()
            communication.map(initial)
        } else {
            communication.map(SearchUserListUi.Base(listOf(SearchUserUi.Empty())))
            delay.add(query.lowercase())
        }
    }

    private suspend fun find(query: String) {
        val raw = repository.search(query)
        val list = raw.map { it.map(mapper) }
        withContext(dispatchersMain) {
            val result =
                if (list.isEmpty()) mutableListOf<SearchUserUi>(SearchUserUi.NoResults())
                else ArrayList(list)
            if (!cleared)
                communication.map(SearchUserListUi.Base(result))
        }
    }

    fun init() {
        communication.map(initial)
    }
}