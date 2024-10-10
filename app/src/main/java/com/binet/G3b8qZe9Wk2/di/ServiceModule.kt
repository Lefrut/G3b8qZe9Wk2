package com.binet.G3b8qZe9Wk2.di

import com.binet.G3b8qZe9Wk2.data.mappers.MedicationMapperImpl
import com.binet.G3b8qZe9Wk2.data.service.BinetService
import com.binet.G3b8qZe9Wk2.data.service.BinetServiceImpl
import com.binet.G3b8qZe9Wk2.data.service.model.MedicationDTO
import com.binet.G3b8qZe9Wk2.domain.model.Mapper
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val serviceModule = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = "shans.d2.i-partner.ru"
                }
            }
        }
    }

    singleOf(::BinetServiceImpl) bind BinetService::class

    singleOf<Mapper<MedicationDTO, Medication>>(::MedicationMapperImpl)

}