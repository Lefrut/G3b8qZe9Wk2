package com.binet.G3b8qZe9Wk2.data.service.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class MedicationDTO(
    @SerialName("categories")
    val categories: CategoriesDTO?,
    @SerialName("description")
    val description: String?,
    @SerialName("documentation")
    val documentation: String?,
    @SerialName("fields")
    val fields: List<FieldDTO?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("name")
    val name: String?
)