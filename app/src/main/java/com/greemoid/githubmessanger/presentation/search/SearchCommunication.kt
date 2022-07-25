package com.greemoid.githubmessanger.presentation.search

import com.greemoid.githubmessanger.presentation.core.Communication

interface SearchCommunication : Communication<SearchUserListUi>{

    class Base : Communication.Base<SearchUserListUi>(), SearchCommunication
}