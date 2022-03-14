package com.example.scalioproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.scalioproject.constant.Constants.constantPageSize
import com.example.scalioproject.data.pagingSource.UserPagingSource
import com.example.scalioproject.data.remote.WebService
import com.example.scalioproject.data.repositoryInterface.UserRepositoryInterface
import javax.inject.Inject

class UsersRepository @Inject constructor(private val webService: WebService) :
    UserRepositoryInterface {

    override fun getUsers(query: String) = Pager(
        config = PagingConfig
            (
            pageSize = constantPageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            UserPagingSource(query = query,webService = webService)
        }
    ).flow
}
