package com.example.scalioproject.data.repositoryInterface

import androidx.paging.PagingData
import com.example.scalioproject.data.model.UserModelResponse
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    fun getUsers(query: String): Flow<PagingData<UserModelResponse>>
}