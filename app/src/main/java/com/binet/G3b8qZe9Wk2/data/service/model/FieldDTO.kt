package com.binet.G3b8qZe9Wk2.data.service.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class FieldDTO(
    @SerialName("flags")
    val flags: FlagsDTO?,
    @SerialName("group")
    val group: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("show")
    val show: Int?,
    @SerialName("type")
    val type: String?,
    @SerialName("value")
    val value: String?
)