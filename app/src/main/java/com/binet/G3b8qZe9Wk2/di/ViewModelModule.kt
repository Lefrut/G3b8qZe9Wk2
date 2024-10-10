package com.binet.G3b8qZe9Wk2.di

import com.binet.G3b8qZe9Wk2.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}