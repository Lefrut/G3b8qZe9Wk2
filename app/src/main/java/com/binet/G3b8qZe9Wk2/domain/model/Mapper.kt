package com.binet.G3b8qZe9Wk2.domain.model

fun interface MedicationMapper<T> : Mapper<T, Medication>

fun interface Mapper<in T, out R> {
    fun map(value: T): R
}




