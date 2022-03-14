package com.example.scalioproject.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scalioproject.constant.Constants.constantPageSize
import com.example.scalioproject.constant.Constants.startingPageIndex
import com.example.scalioproject.data.model.UserModelResponse
import com.example.scalioproject.data.remote.WebService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class UserPagingSource (
    private val query: String,
    private val webService: WebService
) : PagingSource<Int, UserModelResponse>() {


    override fun getRefreshKey(state: PagingState<Int, UserModelResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModelResponse> {
        val pageIndex = params.key ?: 1

        return try {
            val response = webService.getUsers(userQuery = query, pageNumber = pageIndex)
            val users = response.items
            val nextKey = if (users.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / constantPageSize)
            }
            LoadResult.Page(
                data = users,
                prevKey = if (pageIndex == startingPageIndex) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}