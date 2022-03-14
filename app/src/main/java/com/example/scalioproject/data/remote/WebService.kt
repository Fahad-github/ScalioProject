package com.example.scalioproject.data.remote

import com.example.scalioproject.data.model.ResponseItems
import com.example.scalioproject.data.model.UserModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("/search/users")
    suspend fun getUsers(
        @Query("q") userQuery: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int = 9
    ): ResponseItems<UserModelResponse>
}