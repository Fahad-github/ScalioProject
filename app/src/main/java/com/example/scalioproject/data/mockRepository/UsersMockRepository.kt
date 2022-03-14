package com.example.scalioproject.data.mockRepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.scalioproject.data.model.UserModelResponse
import com.example.scalioproject.data.pagingSource.UserPagingSource
import com.example.scalioproject.data.remote.WebService
import com.example.scalioproject.data.repositoryInterface.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersMockRepository@Inject constructor(private val webService: WebService) : UserRepositoryInterface {

    override fun getUsers(query: String): Flow<PagingData<UserModelResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 0, enablePlaceholders = false),
            pagingSourceFactory = {
                UserPagingSource(query = query, webService = webService)
            }
        ).flow
    }
}