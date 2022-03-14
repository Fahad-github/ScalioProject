package com.example.scalioproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.scalioproject.adapter.UsersAdapter
import com.example.scalioproject.databinding.ActivityMainBinding
import com.example.scalioproject.utils.hideKeyboard
import com.example.scalioproject.utils.toast
import com.example.scalioproject.utils.toggleVisibility
import com.example.scalioproject.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    private val viewModel: UserViewModel by viewModels()
    private var usersAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerAdapter()
        setSubmitListener()
        loadingListener()

    }

    private fun loadingListener() {
        lifecycleScope.launch {
            usersAdapter?.loadStateFlow?.collectLatest { it ->
                when (it.refresh) {
                    is LoadState.NotLoading -> binding.shimmerLoading.toggleVisibility(isLoading = false)
                    is LoadState.Loading -> binding.shimmerLoading.toggleVisibility(isLoading = true)
                    is LoadState.Error -> binding.shimmerLoading.toggleVisibility(isLoading = false)
                }
            }
        }
    }

    private fun setSubmitListener() {
        binding.submitButton.setOnClickListener {
            hideKeyboard()
            submitButtonClicked()
        }
    }

    private fun submitButtonClicked() {
        val query = binding.etSearch.text.toString()

        if (query.isEmpty()) {
            toast("Empty input")
            return
        }

        getResults(query = query)
    }

    private fun getResults(query: String) {
        clearList()
        lifecycleScope.launch {
            viewModel.getUsers(query = query).collectLatest {
                usersAdapter?.submitData(it)
            }
        }
    }

    private fun clearList() {
        usersAdapter?.submitData(
            lifecycle = lifecycle,
            pagingData = PagingData.empty()
        )
    }

    private fun setRecyclerAdapter() {
        usersAdapter = UsersAdapter()
        binding.rvUsers.adapter = usersAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        usersAdapter = null
    }

}