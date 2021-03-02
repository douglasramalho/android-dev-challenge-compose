package com.example.androiddevchallenge.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.PetForAdoption
import com.example.androiddevchallenge.data.repository.MeowRepositoryInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MeowRepositoryInterface
) : ViewModel() {

    private val _detailState = MutableLiveData<DetailState>(DetailState.Loading)
    val detailState: LiveData<DetailState> = _detailState

    fun findPetForAdoption(id: Int) {
        viewModelScope.launch {
            delay(DELAY_TIME_IN_MILLIS)

            repository.findPetForAdoption(id)
                .catch { error ->
                    val errorMessage = error.message ?: "Unknown error"

                    Log.d(TAG, errorMessage)

                    _detailState.postValue(
                        DetailState.Error(
                            R.string.detail_screen_error
                        )
                    )
                }.collect { pet ->
                    _detailState.postValue(DetailState.Success(pet))
                }
        }
    }

    class DetailViewModelFactory(
        private val repository: MeowRepositoryInterface
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

    sealed class DetailState {
        object Loading : DetailState()
        class Success(val pet: PetForAdoption) : DetailState()
        class Error(val errorStringResId: Int) : DetailState()
    }

    companion object {
        private val TAG = DetailViewModel::class.java.simpleName
        private const val DELAY_TIME_IN_MILLIS = 2000L
    }
}