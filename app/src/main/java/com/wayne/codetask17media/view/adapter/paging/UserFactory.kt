package com.wayne.codetask17media.view.adapter.paging

import androidx.paging.DataSource
import com.wayne.codetask17media.model.api.vo.User

class UserFactory constructor(
    private val userDataSource: UserDataSource
): DataSource.Factory<Int, User>() {
    override fun create(): DataSource<Int, User> {
        return userDataSource
    }

}