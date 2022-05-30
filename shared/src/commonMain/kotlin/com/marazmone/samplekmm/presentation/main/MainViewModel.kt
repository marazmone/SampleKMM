package com.marazmone.samplekmm.presentation.main

import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel(
    val eventsDispatcher: EventsDispatcher<EventListener>
) : ViewModel(), KoinComponent {

    private val useCase: LaunchesUseCase by inject()

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

        fun onSuccess(result: List<RocketLaunchesModel>)

        fun onError(error: String)
    }
}