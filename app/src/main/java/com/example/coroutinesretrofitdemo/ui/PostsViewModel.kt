package com.example.coroutinesretrofitdemo.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesretrofitdemo.data.Post
import com.example.coroutinesretrofitdemo.network.RetrofitClient
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    var posts = mutableStateListOf<Post>()
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun fetchPosts(onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getPosts()
                posts.clear()
                posts.addAll(response)
                error = null
            } catch (e: Exception) {
                error = e.localizedMessage ?: "Error desconocido"
            } finally {
                onComplete()
            }
        }
    }
}
