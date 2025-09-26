package com.example.coroutinesretrofitdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ApiScreen(viewModel: PostsViewModel = viewModel()) {
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                loading = true
                viewModel.fetchPosts {
                    loading = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cargar Posts")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (loading) {
            CircularProgressIndicator()
        } else {
            viewModel.error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
            LazyColumn {
                items(viewModel.posts.take(10)) { post ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "${post.id}. ${post.title}", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}