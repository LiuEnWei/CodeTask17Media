package com.wayne.codetask17media.view.adapter.paging

import androidx.paging.PageKeyedDataSource
import com.wayne.codetask17media.Constant
import com.wayne.codetask17media.model.api.ApiRepository
import com.wayne.codetask17media.model.api.vo.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import kotlin.math.ceil

/**
 * References :
 * https://docs.github.com/en/rest/reference/search#search-users
 * */
class UserDataSource constructor(
    private val query: String,
    private val viewModelScope: CoroutineScope,
    private val apiRepository: ApiRepository,
    private val pagingListener: PagingListener
): PageKeyedDataSource<Int, User>() {
    var maxPage = 1
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        viewModelScope.launch {
            flow {
                val result = apiRepository.getUsers(query)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    pagingListener.onLoading()
                    Timber.e("onLoading()")
                }.catch { e ->
                    Timber.e("onThrowable(), $e")
                    pagingListener.onThrowable(e)
                }.onCompletion {
                    pagingListener.onLoaded()
                    Timber.e("onLoaded()")
                }.collect {
                    if (it.isSuccessful) {
                        it.body()?.run {
                            maxPage = ceil(this.totalCount.toDouble().div(Constant.GITHUB_SEARCH_USERS_PER_PAGE)).toInt()
                            Timber.e("totalCount : ${this.totalCount}, maxPage : $maxPage")
                            callback.onResult(this.itmes,
                                null,
                                if (this.itmes.size == this.totalCount) {
                                    null
                                } else {
                                    2
                                })
                        }
                    }
                }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val page = params.key
        viewModelScope.launch {
            flow {
                val result = apiRepository.getUsers(query, page)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)
            }.flowOn(Dispatchers.IO).catch { e ->
                    pagingListener.onThrowable(e)
                }.collect {
                    if (it.isSuccessful) {
                        it.body()?.run {
                            callback.onResult(this.itmes,if (maxPage > page) {
                                page + 1
                            } else {
                                null
                            })
                        }
                    }
                }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        // Nothing to do here
    }
}