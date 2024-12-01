package com.divar.create_ads

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.create_ads.component.CreateAdsToolbar
import com.divar.create_ads.component.Step1Content
import com.divar.create_ads.component.Step2Content
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.parameter.Parameter
import com.divar.ui.R
import com.divar.ui.category.CategoryDialog
import com.divar.ui.core.button.AppButton
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier
import com.divar.ui.extension.immutableListOf
import com.divar.ui.parameter_dialog.ParameterDialog
import com.divar.ui.them.AppTheme
import com.divar.utils.OnResume
import com.nareshchocha.filepickerlibrary.models.PickMediaConfig
import com.nareshchocha.filepickerlibrary.models.PickMediaType
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import com.nareshchocha.filepickerlibrary.utilities.appConst.Const
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CreateAdsScreen(
    vm: CreateAdsViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onLocation: () -> Unit,
) {
    val uiState = vm.uiState.collectAsState().value
    LaunchedEffect(key1 = uiState.adsCreated) {
        if (uiState.adsCreated) onBack()
    }
    LaunchedEffect(key1 = uiState.toNeighborhood) {
        if (uiState.toNeighborhood) onLocation()
    }

    OnResume {
        vm.onTriggerEvent(CreateAdsUiEvent.CheckNeighborhood)
    }

    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val filePaths = it.data?.getStringArrayListExtra(Const.BundleExtras.FILE_PATH_LIST) ?: listOf()
            vm.onTriggerEvent(CreateAdsUiEvent.OmImagePicked(filePaths))
        }
        vm.onTriggerEvent(CreateAdsUiEvent.DismissDialog)
    }

    CreateAdsScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        onAction = { vm.onTriggerEvent(it) },
        screenStep = uiState.screenStep,
        createAdsParam = uiState.createAdsParam,
        parameters = uiState.parameters,
        isLoading = uiState.isLoading,
        onClose = onBack
    )

    if (uiState.showCategoryDialog) {
        CategoryDialog(
            modifier = Modifier,
            categories = uiState.allCategories,
            onDismiss = { vm.onTriggerEvent(CreateAdsUiEvent.DismissDialog) },
            onShowAds = {
                vm.onTriggerEvent(CreateAdsUiEvent.OnSelectCategory(it))
            }
        )
    }

    if (uiState.imageIndexChooser != null) {
        pickMedia.launch(
            FilePicker.Builder(context = context)
                .addPickMedia(
                    PickMediaConfig(
                        mPickMediaType = PickMediaType.ImageOnly,
                        maxFiles = 6,
                        allowMultiple = true,
                    )
                )
                .build()
        )
    }

    if (uiState.showParameterDialog != null) {
        ParameterDialog(
            modifier = Modifier,
            parameter = uiState.showParameterDialog,
            onDismiss = { vm.onTriggerEvent(CreateAdsUiEvent.DismissDialog) },
            onSelect = { vm.onTriggerEvent(CreateAdsUiEvent.OnAnswerToParameter(it)) }
        )
    }

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun CreateAdsScreenContent(
    modifier: Modifier = Modifier,
    onAction: OnAction,
    screenStep: ScreenStep,
    createAdsParam: CreateAdsParam = CreateAdsParam(),
    parameters: ImmutableList<Parameter> = immutableListOf(),
    isLoading: Boolean = false,
    onClose: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        containerColor = AppTheme.colors.backgroundColor,
        topBar = {
            CreateAdsToolbar(
                onAction = onAction,
                onClose = onClose
            )
        },
        bottomBar = {
            BottomBar(
                onAction = onAction,
                isLoading = isLoading
            )
        }
    ) {
        when (screenStep) {
            ScreenStep.Step1 -> {
                Step1Content(
                    modifier = Modifier.padding(it),
                    createAdsParam = createAdsParam,
                    onAction = onAction
                )
            }

            ScreenStep.Step2 -> {
                Step2Content(
                    modifier = Modifier.padding(it),
                    createAdsParam = createAdsParam,
                    parameters = parameters,
                    onAction = onAction
                )
            }
        }
        Spacer(modifier = Modifier.padding(it))
    }

}


@Composable
fun BottomBar(onAction: OnAction, isLoading: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(16.dp)
    ) {
        AppButton(
            modifier = Modifier
                .height(55.dp)
                .width(180.dp),
            text = R.string.next,
            isLoading = isLoading,
            onClick = {
                onAction(CreateAdsUiEvent.OnNext)
            }
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CreateAdsScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            screenStep = ScreenStep.Step1,
            onAction = {}
        )
    }
}
