package com.binet.G3b8qZe9Wk2.data.service

import com.binet.G3b8qZe9Wk2.data.service.model.MedicationDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.path

class BinetServiceImpl(
    private val client: HttpClient
) : BinetService {

    override suspend fun getItems(search: String?): List<MedicationDTO> {
        val response = client.request {
            url.path("/api/ppp/index")
            parameter("search", search)
        }
        return response.body<List<MedicationDTO>>()
    }

    override suspend fun getItem(id: Int): MedicationDTO {
        val response = client.request {
            url.path("/api/ppp/item")
            parameter("id", id)
        }
        return response.body<MedicationDTO>()
    }


}