package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.MemoryMeowFinderData
import com.example.androiddevchallenge.data.model.PetForAdoption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MemoryDataSource {

    fun getAllPetsForAdoption(): Flow<List<PetForAdoption>> {
        return flow {
            emit(MemoryMeowFinderData.catsForAdoption)
        }
    }

    fun findPetForAdoptionById(id: Int): Flow<PetForAdoption> {
        return flow {
            emit(MemoryMeowFinderData.catsForAdoption.first { cat ->
                cat.id == id
            })
        }
    }
}