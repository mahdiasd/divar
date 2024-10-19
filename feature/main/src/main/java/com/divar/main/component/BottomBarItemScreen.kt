package com.divar.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.divar.main.MainUiEvent
import com.divar.main.OnAction
import com.divar.main.fake_data.MainFakeData
import com.divar.main.model.BottomBarItem
import com.divar.ui.core.text.LabelSmallText
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun BottomBarItemScreen(
    bottomBarItems: ImmutableList<BottomBarItem>,
    selectedIndex: Int,
    onAction: OnAction
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
            .height(55.dp),
        containerColor = AppTheme.colors.itemColor
    ) {
        bottomBarItems.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onAction(MainUiEvent.OnChangeTab(index)) },
                label = { LabelSmallText(text = stringResource(id = bottomBarItem.title)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppTheme.colors.primaryColor,
                    unselectedIconColor = AppTheme.colors.iconColor,
                    indicatorColor = AppTheme.colors.itemColor

                ),
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = bottomBarItem.icon),
                        contentDescription = "home"
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        BottomBarItemScreen(
            bottomBarItems = MainFakeData.provideBottomBars().reversed().toImmutableList(),
            selectedIndex = 0,
            onAction = {}
        )
    }
}
