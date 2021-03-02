package com.example.androiddevchallenge.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.PetForAdoption
import com.example.androiddevchallenge.data.repository.MeowRepositoryInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HomeViewModel(
    private val repository: MeowRepositoryInterface
) : ViewModel() {

    private val _homeState = MutableLiveData<HomeState>(HomeState.Loading)
    val homeState: LiveData<HomeState> = _homeState

    init {
        viewModelScope.launch {
            delay(DELAY_TIME_IN_MILLIS)

            repository.getAllPetsForAdoption()
                .catch { error ->
                    val errorMessage = error.message ?: "Unknown error"

                    Log.d(TAG, errorMessage)

                    _homeState.postValue(
                        HomeState.Error(R.string.home_screen_error)
                    )
                }
                .collect { cats ->
                    _homeState.postValue(HomeState.Success(cats))
                }
        }
    }

    class HomeViewModelFactory(
        private val repository: MeowRepositoryInterface
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

    sealed class HomeState {
        object Loading : HomeState()
        class Success(val pets: List<PetForAdoption>) : HomeState()
        class Error(val errorStringResId: Int) : HomeState()
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
        private const val DELAY_TIME_IN_MILLIS = 2000L
    }
}