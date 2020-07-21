package com.wayne.codetask17media.view.adapter.paging

interface PagingListener {
    fun onLoading()
    fun onLoaded()
    fun onTotalCount(count: Int)
    fun onThrowable(throwable: Throwable)
}