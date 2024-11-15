package com.divar.data.mapper.parameter

import com.divar.domain.model.parameter.ParameterAnswer
import com.divar.network.dto.parameter.ParameterAnswerResponse

fun ParameterAnswerResponse.toDomain(): ParameterAnswer {
    return ParameterAnswer(
        answer = answer,
        parameter = parameter.toDomain()
    )
}