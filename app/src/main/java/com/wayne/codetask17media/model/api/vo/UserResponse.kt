package com.wayne.codetask17media.model.api.vo

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("total_count") val totalCount: Int,
                        @SerializedName("incomplete_results") val incompleteResults: Boolean,
                        @SerializedName("items") val itmes: List<User>)