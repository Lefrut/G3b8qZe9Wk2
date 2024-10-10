package com.binet.G3b8qZe9Wk2.domain.repository

import com.binet.G3b8qZe9Wk2.domain.model.Medication

interface BinetRepository {


    suspend fun getMedications(search: String? = null): Result<List<Medication>>

    suspend fun getMedication(id: Int): Result<Medication>

}