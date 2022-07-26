package com.greemoid.githubmessanger.sl

import com.greemoid.githubmessanger.data.search.SearchUserRepository
import com.greemoid.githubmessanger.presentation.search.SearchCommunication
import com.greemoid.githubmessanger.presentation.search.SearchResultsMapper
import com.greemoid.githubmessanger.presentation.search.SearchViewModel
import com.greemoid.githubmessanger.sl.core.BaseModule
import com.greemoid.githubmessanger.sl.core.CoreModule

class SearchModule(private val coreModule: CoreModule) : BaseModule<SearchViewModel> {

    override fun viewModel(): SearchViewModel = SearchViewModel(
        SearchCommunication.Base(),
        SearchResultsMapper.Base(),
        SearchUserRepository.Base(coreModule.firebaseDatabaseProvider()),
    )
}