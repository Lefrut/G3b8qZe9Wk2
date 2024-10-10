package com.binet.G3b8qZe9Wk2.domain.model

data class Medication(
    val id: Int,
    val imageUrl: String,
    val iconUrl: String,
    val name: String,
    val description: String,
    val categoryName: String,
    val categoryType: CategoryType
)

