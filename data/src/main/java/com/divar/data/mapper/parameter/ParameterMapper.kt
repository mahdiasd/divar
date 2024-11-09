package com.divar.data.mapper.parameter

import com.divar.data.mapper.category.toDomain
import com.divar.domain.model.parameter.Parameter
import com.divar.network.dto.ads.ParameterAnswerRequest
import com.divar.network.dto.parameter.ParameterResponse

fun ParameterResponse.toDomain(): Parameter {
    return Parameter(
        id = id,
        name = name,
        dataType = dataType.toDomain(),
        acceptedOptions = acceptedOptions,
    )
}

fun Parameter.toAnswerRequest(): ParameterAnswerRequest? {
    if (answer.isNullOrEmpty()) return null
    return ParameterAnswerRequest(
        answer = answer ?: "", parameterId = id
    )
}


