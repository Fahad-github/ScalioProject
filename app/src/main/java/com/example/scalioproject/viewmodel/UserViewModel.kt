package com.example.scalioproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.scalioproject.data.mapper.UserMapper
import com.example.scalioproject.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val mapper: UserMapper
) : ViewModel() {

    fun getUsers(query: String) =
        repository.getUsers(query = query).map { pagingData ->
            pagingData.map {
                mapper.mapRemoteUserToUi(it)
            }
        }.cachedIn(viewModelScope)
}