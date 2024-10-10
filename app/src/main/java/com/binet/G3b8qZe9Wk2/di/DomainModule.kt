package com.binet.G3b8qZe9Wk2.di

import com.binet.G3b8qZe9Wk2.data.repository.BinetRepositoryImpl
import com.binet.G3b8qZe9Wk2.domain.repository.BinetRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    singleOf(::BinetRepositoryImpl) bind BinetRepository::class

}