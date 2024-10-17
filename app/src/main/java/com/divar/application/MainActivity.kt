package com.divar.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.divar.application.navigation.AppNavigation
import com.divar.splash.SplashScreen
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.core.text.TitleLargeText
import com.divar.ui.them.AppTheme
import com.divar.ui.them.StatusBarDark
import com.divar.ui.them.StatusBarLight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * This line is required before onCreate to ensure that
         * applyPostSplashScreenTheme (in theme) functions correctly.
         * https://stackoverflow.com/a/78445185/10180275
         * */
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            if (isSystemInDarkTheme()) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarDark.toArgb())
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb()),
                    navigationBarStyle = SystemBarStyle.dark(StatusBarLight.toArgb())
                )
            }
            AppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding()
            .background(Color.Red)
            .padding(16.dp)
    ) {
        BodyMediumText(text = "This is first")
        TitleLargeText(text = "This is second")
        LabelMediumText(text = "This is third")
    }


//
//    Button(
//        onClick = { }) {
//        Text(text = "Hello $name!")
//    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize())
        {
            Greeting("Android2")
        }
    }
}