package com.binet.G3b8qZe9Wk2.data.service

import com.binet.G3b8qZe9Wk2.data.service.model.MedicationDTO

interface BinetService {


    suspend fun getItems(search: String?): List<MedicationDTO>

    suspend fun getItem(id: Int): MedicationDTO

}