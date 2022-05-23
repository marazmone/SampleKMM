package com.marazmone.samplekmm.android.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marazmone.samplekmm.android.R
import com.marazmone.samplekmm.android.di.factory.ViewModelFactory
import com.marazmone.samplekmm.android.presentation.adapter.LaunchesRvAdapter
import com.marazmone.samplekmm.android.presentation.base.action.ProgressDelegate
import com.marazmone.samplekmm.android.presentation.base.action.doOnResult
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main), ProgressDelegate {

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

    override fun hide() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun show() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.launchesLiveData
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { action ->
                    action.doOnResult(
                        progressDelegate = this@MainActivity,
                        onSuccess = { result ->
                            launchesRvAdapter.launches = result
                            launchesRvAdapter.notifyDataSetChanged()
                        },
                        onError = { error, _ ->
                            Toast
                                .makeText(this@MainActivity, error, Toast.LENGTH_LONG)
                                .show()
                            Log.e("wtf", error)
                        },
                    )
                }
        }
    }
}
