package com.kaushik.demostandardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaushik.demostandardapp.adapter.PostAdapter
import com.kaushik.demostandardapp.databinding.ActivityMainBinding
import com.kaushik.demostandardapp.main.MainViewModel
import com.kaushik.demostandardapp.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

const val TAG = "Main Activity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        viewModel.getPost()

        binding.retry.setOnClickListener{
            viewModel.getPost()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state->
                when(state){
                    is ApiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                        binding.retry.visibility =View.VISIBLE
                        Log.d(TAG,state.errorMessage.localizedMessage)
                    }
                    is ApiState.Loading -> {
                        binding.retry.visibility =View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    is ApiState.Success -> {
                        binding.retry.visibility =View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        postAdapter.setData(state.data)
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}