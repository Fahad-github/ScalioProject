package com.example.scalioproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scalioproject.constant.Constants.dummyImageUrl
import com.example.scalioproject.data.model.UserModel
import com.example.scalioproject.databinding.ItemUserBinding
import com.example.scalioproject.utils.loadImageFromUrl

class UsersAdapter : PagingDataAdapter<UserModel, UserViewHolder>(UserDiffCallback()) {


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}

class UserViewHolder(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UserModel?) {
        binding.avatar.loadImageFromUrl(item?.avatarUrl ?: dummyImageUrl)
        binding.login.text = item?.login ?: "Github User"
        binding.type.text = item?.type ?: "Github type"
    }
}
