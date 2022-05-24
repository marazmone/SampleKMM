package com.marazmone.samplekmm.android.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marazmone.samplekmm.android.R
import com.marazmone.samplekmm.android.presentation.main.adapter.LaunchesRvAdapter
import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.presentation.base.BaseActivity
import com.marazmone.samplekmm.presentation.main.MainViewModel
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain

class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main),
    MainViewModel.EventListener {

    private lateinit var launchesRecyclerView: RecyclerView

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val launchesRvAdapter = LaunchesRvAdapter(listOf())

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun viewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { MainViewModel(eventsDispatcherOnMain()) }

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

        viewModel.getLaunches()
        viewModel.eventsDispatcher.bind(this, this)
    }

    override fun onSuccess(result: List<RocketLaunchEntitys>) {
        launchesRvAdapter.launches = result
        launchesRvAdapter.notifyDataSetChanged()
    }

    override fun onError(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
        Log.e("wtf", error)
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }
}
