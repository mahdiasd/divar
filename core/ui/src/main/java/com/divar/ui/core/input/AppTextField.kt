package com.divar.ui.core.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.them.AppTheme


@Preview
@Composable
private fun TextFieldPrev() {
    AppTheme {
        AppTextField(value = "Name", onValueChange = {}, hint = "")
    }
}

@Preview
@Composable
private fun TextFieldIconPrev() {
    AppTheme {
//        AppTextField(value = "Name", onValueChange = {}, hint = "", icon = )
    }
}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    maxLines: Int = 1,
    minLines: Int = 1,
    shape: CornerBasedShape = AppTheme.shapes.roundSmall,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppTheme.colors.primaryColor,
        unfocusedTextColor = AppTheme.colors.hintColor,
        disabledTextColor = AppTheme.colors.hintColor,
        focusedBorderColor = AppTheme.colors.primaryColor,
        unfocusedBorderColor = AppTheme.colors.hintColor,
        disabledBorderColor = AppTheme.colors.hintColor,
        focusedContainerColor = AppTheme.colors.itemColor,
        unfocusedContainerColor = AppTheme.colors.itemColor,
        disabledContainerColor = AppTheme.colors.itemColor,
    ),
    textStyle: TextStyle = AppTheme.typography.bodyMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    hintTextStyle: TextStyle = AppTheme.typography.bodyMedium.copy(color = AppTheme.colors.hintColor),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    actionNext: Boolean = maxLines == 1
) {


    OutlinedTextField(
        modifier = modifier,
        value = value,
        shape = shape,
        onValueChange = onValueChange,
        maxLines = maxLines,
        textStyle = textStyle.copy(textAlign = textAlign),
        readOnly = readOnly,
        minLines = minLines,
        colors = colors,
        keyboardActions = keyboardActions,
        keyboardOptions = if (actionNext) keyboardOptions.copy(imeAction = ImeAction.Next) else keyboardOptions,
        placeholder = {
            BodyMediumText(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                textStyle = hintTextStyle,
                color = if (value.isEmpty()) AppTheme.colors.hintColor else Color.White
            )
        },
    )
}


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    maxLine: Int = 1,
    shape: CornerBasedShape = AppTheme.shapes.roundSmall,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppTheme.colors.primaryColor,
        unfocusedTextColor = AppTheme.colors.hintColor,
        disabledTextColor = AppTheme.colors.hintColor,
        focusedBorderColor = AppTheme.colors.primaryColor,
        unfocusedBorderColor = AppTheme.colors.hintColor,
        disabledBorderColor = AppTheme.colors.hintColor,
        focusedContainerColor = AppTheme.colors.itemColor,
        unfocusedContainerColor = AppTheme.colors.itemColor,
        disabledContainerColor = AppTheme.colors.itemColor,
    ),
    textStyle: TextStyle = AppTheme.typography.bodyMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    hintTextStyle: TextStyle = AppTheme.typography.bodyMedium.copy(color = AppTheme.colors.hintColor),
    readOnly: Boolean = false,
    enabled: Boolean = readOnly,
    icon: Int,
    iconModifier: Modifier = Modifier.size(16.dp),
    iconTint: Color = AppTheme.colors.hintColor,
    onClick: (() -> Unit)? = null
) {

    OutlinedTextField(
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        value = value,
        shape = shape,
        onValueChange = onValueChange,
        maxLines = maxLine,
        textStyle = textStyle,
        readOnly = readOnly,
        enabled = enabled,
        colors = colors,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            Icon(
                modifier = iconModifier,
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = iconTint
            )
        },
        placeholder = {
            BodyMediumText(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                textStyle = hintTextStyle,
                color = if (value.isEmpty()) AppTheme.colors.hintColor else Color.White
            )
        },
    )


}



