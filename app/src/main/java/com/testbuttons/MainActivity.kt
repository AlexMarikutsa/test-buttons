package com.testbuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testbuttons.ui.components.BlueButton
import com.testbuttons.ui.components.GreenButton
import com.testbuttons.ui.theme.TestButtonsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestButtonsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember {
                        mutableStateOf(Screen.GreenButton)
                    }

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            when (currentScreen) {
                                Screen.GreenButton -> GreenButton()
                                Screen.BlueButton -> BlueButton()
                            }
                        }
                        Button(
                            modifier = Modifier
                                .padding(top = 100.dp)
                                .align(Alignment.TopCenter),
                            onClick = {
                                currentScreen = when (currentScreen) {
                                    Screen.GreenButton -> Screen.BlueButton
                                    Screen.BlueButton -> Screen.GreenButton
                                }
                            }
                        ) {
                            Text("Change button")
                        }
                    }
                }
            }
        }
    }
}

private enum class Screen {
    GreenButton,
    BlueButton
}