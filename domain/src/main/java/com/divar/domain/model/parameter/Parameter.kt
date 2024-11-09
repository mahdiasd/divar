package com.divar.domain.model.parameter

import kotlinx.serialization.Serializable

@Serializable
data class Parameter(
    val id: Long,

    val name: String,

    val dataType: DataType,

    val acceptedOptions: List<String>? = null,

    val answer: String? = null
)

@Serializable
enum class DataType {
    StringInput,
    NumberInput,
    FloatInput,
    CheckBoxInput,
    FixedOption
}