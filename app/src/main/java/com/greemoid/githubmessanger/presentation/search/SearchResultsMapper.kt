package com.greemoid.githubmessanger.presentation.search

import com.greemoid.githubmessanger.data.search.SearchData

interface SearchResultsMapper : SearchData.SearchMapper<SearchUserUi> {

    class Base : SearchResultsMapper {
        override fun map(id: String, name: String, photoUrl: String): SearchUserUi {
            return SearchUserUi.Base(id, name, photoUrl)
        }
    }
}