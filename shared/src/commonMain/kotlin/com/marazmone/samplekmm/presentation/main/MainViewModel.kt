package com.marazmone.samplekmm.presentation.main

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.di.LaunchesModule
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    val eventsDispatcher: EventsDispatcher<EventListener>
) : ViewModel() {

    private val useCase: LaunchesUseCase = LaunchesModule.launchesUseCase

    init {
        getLaunches()
    }

    fun getLaunches() {
        viewModelScope.launch {
            eventsDispatcher.dispatchEvent { showLoading() }
            useCase.execute().doOnResult(
                onSuccess = { rocketLaunches ->
                    eventsDispatcher.dispatchEvent {
                        onSuccess(rocketLaunches)
                        hideLoading()
                    }
                },
                onError = { error ->
                    eventsDispatcher.dispatchEvent {
                        onError(error.msg)
                        hideLoading()
                    }
                }
            )
        }
    }

    interface EventListener {

        fun showLoading()

        fun hideLoading()

        fun onSuccess(result: List<RocketLaunchEntitys>)

        fun onError(error: String)
    }
}