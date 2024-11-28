package com.divar.create_ads

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.create_ads.component.CreateAdsToolbar
import com.divar.create_ads.component.Step1Content
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.ui.R
import com.divar.ui.category.CategoryDialog
import com.divar.ui.core.button.AppButton
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import com.himanshoe.pluck.ui.Pluck
import com.himanshoe.pluck.ui.permission.Permission

@Composable
fun CreateAdsScreen(
    vm: CreateAdsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState = vm.uiState.collectAsState().value

    CreateAdsScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        onAction = { vm.onTriggerEvent(it) },
        screenStep = uiState.screenStep,
        createAdsParam = uiState.createAdsParam,
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
        Pluck(onPhotoSelected = {
            vm.onTriggerEvent(CreateAdsUiEvent.OmImagePicked(it.map { it1 -> it1.uri }))
        })
//        Permission(
//            permissions = uiState.permissions,
//            goToAppSettings = {}
//        ) {
//
//        }
    }

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun CreateAdsScreenContent(
    modifier: Modifier = Modifier,
    onAction: OnAction,
    screenStep: ScreenStep,
    createAdsParam: CreateAdsParam = CreateAdsParam(),
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
            BottomBar(onAction = onAction)
        }
    ) {
        when (screenStep) {
            ScreenStep.Step1 -> Step1Content(
                modifier = Modifier.padding(it),
                createAdsParam = createAdsParam,
                onAction = onAction
            )

            ScreenStep.Step2 -> {}
        }
        Spacer(modifier = Modifier.padding(it))
    }

}


@Composable
fun BottomBar(onAction: OnAction) {
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
