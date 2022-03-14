package com.example.scalioproject.data.mapper

import com.example.scalioproject.data.model.UserModel
import com.example.scalioproject.data.model.UserModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserMapper {

    suspend fun mapRemoteUsersListToUi(
        remoteUsers: List<UserModelResponse>
    ): List<UserModel> {
        return withContext(Dispatchers.Default) {
            remoteUsers.map {
                mapRemoteUserToUi(it)
            }
        }.sortedBy { it.login }
    }

    fun mapRemoteUserToUi(remoteUserModel: UserModelResponse):UserModel {
        return UserModel(remoteUserModel)
    }
}