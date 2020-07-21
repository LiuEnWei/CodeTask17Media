package com.wayne.codetask17media.model.api

import com.wayne.codetask17media.Constant
import com.wayne.codetask17media.model.api.vo.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApiService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/search/users")
    suspend fun getUsers(@Query("q") q: String,
                         @Query("per_page") perPage: Int = Constant.GITHUB_SEARCH_USERS_PER_PAGE,
                         @Query("page") page: Int): Response<UserResponse>
}