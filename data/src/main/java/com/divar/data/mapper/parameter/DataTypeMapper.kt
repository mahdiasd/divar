package com.divar.data.mapper.parameter

import com.divar.domain.model.parameter.DataType
import com.divar.network.dto.parameter.DataTypeResponse

fun DataTypeResponse.toDomain(): DataType {
    return when (this) {
        DataTypeResponse.StringInput -> DataType.StringInput
        DataTypeResponse.NumberInput -> DataType.NumberInput
        DataTypeResponse.FloatInput -> DataType.FloatInput
        DataTypeResponse.CheckBoxInput -> DataType.CheckBoxInput
        DataTypeResponse.FixedOption -> DataType.FixedOption
    }
}