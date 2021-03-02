package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.model.PetForAdoption
import kotlinx.coroutines.flow.Flow

interface MeowRepositoryInterface {

    fun getAllPetsForAdoption(): Flow<List<PetForAdoption>>

    fun findPetForAdoption(id: Int): Flow<PetForAdoption>
}