package com.marazmone.samplekmm.android.presentation

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marazmone.samplekmm.android.R
import com.marazmone.samplekmm.android.di.factory.ViewModelFactory
import com.marazmone.samplekmm.android.presentation.adapter.LaunchesRvAdapter
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var launchesRecyclerView: RecyclerView

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val launchesRvAdapter = LaunchesRvAdapter(listOf())

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"

        launchesRecyclerView = findViewById(R.id.launchesListRv)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        launchesRecyclerView.adapter = launchesRvAdapter
        launchesRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getLaunches()
        }
        observeData()
    }

    private fun observeData() {
        viewModel.launchesLiveData.observe(this) { action ->
            when (action) {
                is MainViewModel.LaunchesAction.Success -> {
                    swipeRefreshLayout.isRefreshing = false
                    launchesRvAdapter.launches = action.result
                    launchesRvAdapter.notifyDataSetChanged()
                }
                is MainViewModel.LaunchesAction.Error -> {
                    Toast
                        .makeText(this, action.exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    swipeRefreshLayout.isRefreshing = false
                    Log.e("wtf", action.exception.localizedMessage, action.exception)
                }
                is MainViewModel.LaunchesAction.Loading -> {
                    swipeRefreshLayout.isRefreshing = true
                }
            }
        }
    }
}
