package com.ch000se.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ch000se.messenger.core.common.android.AndroidExceptionHandler
import com.ch000se.messenger.feature.init.presentation.InitScreen
import com.ch000se.messenger.ui.theme.MessengerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var exceptionHandler: AndroidExceptionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessengerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    exceptionHandler.ErrorDialog()
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    InitScreen(modifier = modifier)
}