package com.divar.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.divar.ui.extension.toComposePx

@Composable
fun coilCircle(data: Any): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .crossfade(true)
        .transformations(CircleCropTransformation())
        .build()
}

@Composable
fun coilRounded(data: Any, radiusInDp: Float): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .size(coil.size.Size.ORIGINAL)
        .crossfade(true)
        .listener(
            onError = { _, throwable ->
                // Log or handle the error
                throwable.throwable.message.dLog()
            }
        )
        .transformations(
            RoundedCornersTransformation(radiusInDp.dp.toComposePx()),
        )
        .build()
}


@Composable
fun coilRounded(
    data: Any,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomLeft: Float = 0f,
    bottomRight: Float = 0f
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .size(coil.size.Size.ORIGINAL)
        .crossfade(true)
        .transformations(
            RoundedCornersTransformation(
                topLeft = topLeft.dp.toComposePx(),
                topRight = topRight.dp.toComposePx(),
                bottomLeft = bottomLeft.dp.toComposePx(),
                bottomRight = bottomRight.dp.toComposePx()
            ),
        )
        .build()
}

@Composable
fun defaultCoil(
    data: String,
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .listener(onError = { request, result ->
            result.throwable.message.dLog("")
        })
        .size(coil.size.Size.ORIGINAL)
        .crossfade(true)
        .build()
}

@Composable
fun svgCoil(
    data: String,
): ImageRequest {
    return ImageRequest.Builder(LocalContext.current)
        .data(data)
        .listener(onError = { request, result ->
            result.throwable.message.dLog("")
        })
        .decoderFactory(SvgDecoder.Factory())
        .size(coil.size.Size.ORIGINAL)
        .crossfade(true)
        .build()
}
