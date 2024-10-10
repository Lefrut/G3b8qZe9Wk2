package com.binet.G3b8qZe9Wk2.data.repository

import com.binet.G3b8qZe9Wk2.data.service.BinetService
import com.binet.G3b8qZe9Wk2.data.service.model.MedicationDTO
import com.binet.G3b8qZe9Wk2.domain.model.Mapper
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import com.binet.G3b8qZe9Wk2.domain.repository.BinetRepository

class BinetRepositoryImpl(
    private val mapper: Mapper<MedicationDTO, Medication>,
    private val binetService: BinetService
) : BinetRepository {
    override suspend fun getMedications(search: String?): Result<List<Medication>> =
        kotlin.runCatching {
            val items = binetService.getItems(search)
            val medications = items.tryMapNotNull { mapper.map(it) }
            return@runCatching medications
        }

    override suspend fun getMedication(id: Int): Result<Medication> = kotlin.runCatching {
        val item = binetService.getItem(id)
        val medication = mapper.map(item)
        return@runCatching medication
    }
}

inline fun <T, R : Any> Iterable<T>.tryMapNotNull(
    onCatch: (T) -> R? = { null },
    transform: (T) -> R
): List<R> {
    return mapNotNull {
        return@mapNotNull try {
            transform(it)
        } catch (ex: Throwable) {
            onCatch(it)
        }
    }
}