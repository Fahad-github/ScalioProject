package com.example.scalioproject.data.model

import com.google.gson.annotations.SerializedName

data class GetUsersResponse(
    @SerializedName("total_count") var totalCount: Int = -1,
    @SerializedName("incomplete_results") var incompleteResults: Boolean = false,
    @SerializedName("items") var items: ArrayList<UserModelResponse> = arrayListOf()
)