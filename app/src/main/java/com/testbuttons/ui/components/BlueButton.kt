package com.testbuttons.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testbuttons.R
import com.testbuttons.ui.theme.TestButtonsTheme


@Composable
fun BlueButton() {
    val lightBlue = Color(0xFF7d8eb8)
    val darkBlue = Color(0xFF7286b3)

    var isPressed by remember { mutableStateOf(false) }
    val buttonDepth = 4.dp
    val offset: Dp by animateDpAsState(
        targetValue = if (isPressed) buttonDepth else 0.dp,
        label = "Button animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(26.dp),
            painter = painterResource(id = R.drawable.img_example),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (offset == 0.dp) Color(0xFF7d8eb8) else Color(0xFF6d7da3).copy(alpha = 0.8f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(y = offset)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isPressed = true
                                try {
                                    awaitRelease()
                                } finally {
                                    isPressed = false
                                }
                            }
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .offset(y = buttonDepth)
                        .matchParentSize()
                        .padding(1.dp)
                        .alpha(if (offset == 0.dp) 0.2f else 0f)
                        .background(
                            color = Color.Black,
                            shape = MaterialTheme.shapes.large
                        )
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFF434447)
                                .copy(
                                    if (offset == buttonDepth) {
                                        1f
                                    } else {
                                        0f
                                    }
                                ),
                            shape = MaterialTheme.shapes.large
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(1.dp)
                            .then(
                                if (offset == 0.dp) {
                                    Modifier
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0xFF707d9c),
                                                    lightBlue
                                                )
                                            ),
                                            shape = MaterialTheme.shapes.large
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color(0xFFc1cde8),
                                            shape = MaterialTheme.shapes.large
                                        )
                                } else {
                                    Modifier
                                        .background(
                                            color = darkBlue,
                                            shape = MaterialTheme.shapes.large
                                        )
                                }
                            )
                            .padding(vertical = 14.dp, horizontal = 46.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GreenButton_Preview() {
    TestButtonsTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            BlueButton()
        }
    }
}