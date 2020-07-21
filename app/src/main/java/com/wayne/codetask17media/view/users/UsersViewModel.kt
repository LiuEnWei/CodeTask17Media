package com.wayne.codetask17media.view.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wayne.codetask17media.Constant
import com.wayne.codetask17media.model.api.ApiRepository
import com.wayne.codetask17media.model.api.vo.User
import com.wayne.codetask17media.view.adapter.paging.PagingListener
import com.wayne.codetask17media.view.adapter.paging.UserDataSource
import com.wayne.codetask17media.view.adapter.paging.UserFactory
import com.wayne.codetask17media.view.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.inject
import timber.log.Timber

class UsersViewModel: BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    private val _usersData = MutableLiveData<PagedList<User>>()
    val usersData: LiveData<PagedList<User>> = _usersData

    fun getUsers(query: String = "", listener: PagingListener) {
        Timber.e("getUsers : $query")
        viewModelScope.launch {
            getPagingItems(query, listener).asFlow().collect {
                _usersData.postValue(it)
            }
        }
    }

    private fun getPagingItems(query: String, listener: PagingListener): LiveData<PagedList<User>> {
        val userDataSource = UserDataSource(query, viewModelScope, apiRepository, listener)
        val userFactory = UserFactory(userDataSource)
        val config = PagedList.Config.Builder()
            .setPageSize(Constant.GITHUB_SEARCH_USERS_PER_PAGE)
            .build()
        return LivePagedListBuilder(userFactory, config).build()
    }
}