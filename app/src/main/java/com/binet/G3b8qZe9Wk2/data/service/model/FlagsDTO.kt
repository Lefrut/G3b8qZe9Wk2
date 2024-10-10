package com.binet.G3b8qZe9Wk2.data.service.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class FlagsDTO(
    @SerialName("html")
    val html: Int?,
    @SerialName("no_image")
    val noImage: Int?,
    @SerialName("no_name")
    val noName: Int?,
    @SerialName("no_value")
    val noValue: Int?,
    @SerialName("no_wrap")
    val noWrap: Int?,
    @SerialName("no_wrap_name")
    val noWrapName: Int?
)