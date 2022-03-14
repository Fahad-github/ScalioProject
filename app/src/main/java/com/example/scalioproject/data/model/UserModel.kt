package com.example.scalioproject.data.model

class UserModel(model:UserModelResponse?) {
    var id = model?.id ?: -1
    var avatarUrl = model?.avatarUrl ?: ""
    var login = model?.login ?: ""
    var type = model?.type ?: ""
}