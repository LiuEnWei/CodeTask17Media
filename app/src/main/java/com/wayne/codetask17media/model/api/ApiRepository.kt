package com.wayne.codetask17media.model.api

import com.wayne.codetask17media.model.api.vo.UserResponse
import retrofit2.Response

class ApiRepository(private val githubApiService: GithubApiService) {

    suspend fun getUsers(q: String, page: Int = 1): Response<UserResponse> {
        return githubApiService.getUsers(q = q, page = page)
    }
}