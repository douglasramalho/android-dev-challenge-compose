package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.model.PetForAdoption
import kotlinx.coroutines.flow.Flow

class MeowRepositoryImpl(
    private val memoryDataSource: MemoryDataSource
) : MeowRepositoryInterface {

    override fun getAllPetsForAdoption(): Flow<List<PetForAdoption>> {
        return memoryDataSource.getAllPetsForAdoption()
    }

    override fun findPetForAdoption(id: Int): Flow<PetForAdoption> {
        return memoryDataSource.findPetForAdoptionById(id)
    }
}