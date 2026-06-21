package com.danielyan.gopeak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.retainedComponent
import com.danielyan.gopeak.decompose.AppComponentContext
import com.danielyan.gopeak.decompose.AppRouterImpl
import com.danielyan.gopeak.decompose.DefaultAppComponentContext
import com.danielyan.gopeak.root.api.RootComponent
import com.danielyan.gopeak.root.impl.RootScreen
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val rootComponentFactory by inject<RootComponent.Factory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val rootComponent = retainedComponent { componentContext ->
            rootComponentFactory(
                appComponentContext = object :
                    AppComponentContext by DefaultAppComponentContext(componentContext) {
                    override val appRouter = AppRouterImpl()
                }
            )
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                rootComponent.uiState.value.showSplash
            }
        }

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) {
                    darkColorScheme()
                } else {
                    lightColorScheme()
                },
                motionScheme = MotionScheme.expressive()
            ) {
                RootScreen(rootComponent)
            }
        }
    }
}