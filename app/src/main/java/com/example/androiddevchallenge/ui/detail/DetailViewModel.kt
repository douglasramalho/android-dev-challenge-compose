/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
