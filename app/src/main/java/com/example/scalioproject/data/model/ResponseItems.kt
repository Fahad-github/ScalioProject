package com.example.scalioproject.data.model

import com.google.gson.annotations.SerializedName

class ResponseItems<T>(@SerializedName("items") val items: List<T>) {
}