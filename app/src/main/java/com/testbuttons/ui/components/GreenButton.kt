package com.testbuttons.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testbuttons.R
import com.testbuttons.ui.theme.TestButtonsTheme

val mainGreen = Color(0xFF43A980)
val frontGreen = Color(0xFF50B58D)

@Composable
fun GreenButton() {
    val buttonShape = RoundedCornerShape(18.dp)
    var isPressed by remember { mutableStateOf(false) }
    val buttonDepth = 17.dp
    val offset: Dp by animateDpAsState(
        targetValue = if (isPressed) buttonDepth else 0.dp,
        label = "Button animation"
    )

    Box(
        modifier = Modifier
    ) {
        if (!isPressed) {
            Box(
                modifier = Modifier
                    .offset(y = buttonDepth)
                    .matchParentSize()
                    .graphicsLayer {
                        shadowElevation = 3.dp.toPx()
                        translationY = 3.dp.toPx()
                        shape = buttonShape
                        clip = true
                    }
            )
        }
        Box(
            modifier = Modifier
                .offset(y = buttonDepth)
                .matchParentSize()
                .background(
                    color = mainGreen,
                    shape = buttonShape
                )
        )
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
                .background(
                    color = mainGreen,
                    shape = buttonShape
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF).copy(alpha = 0.36f),
                                Color(0xFFFFFFFF)
                                    .copy(alpha = if (isPressed) 0.6f else 0.74f)
                            )
                        ),
                        shape = buttonShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .background(
                            color = frontGreen,
                            shape = buttonShape
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .width(222.dp)
                            .height(218.dp)
                            .then(
                                if (!isPressed) {
                                    Modifier
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0xFFFFFFFF).copy(alpha = 0.13f),
                                                    Color(0xFFFFFFFF).copy(alpha = 0f),
                                                    Color(0xFFFFFFFF).copy(alpha = 0.3f)
                                                )
                                            ),
                                            shape = buttonShape
                                        )
                                } else {
                                    Modifier
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0xFFFFFFFF).copy(alpha = 0.13f),
                                                    Color(0xFFFFFFFF).copy(alpha = 0f),
                                                    Color(0xFFFFFFFF).copy(alpha = 0.2f)
                                                )
                                            ),
                                            shape = buttonShape
                                        )
                                }
                            )

                    ) {
                        Text(
                            text = "Старт",
                            style = TextStyle(
                                fontSize = 38.sp,
                                fontFamily = FontFamily(Font(R.font.pt_sans)),
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                shadow = Shadow(
                                    color = Color.Gray,
                                    offset = Offset(0f, 1f),
                                    blurRadius = 2f
                                )
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
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
//            contentAlignment = Alignment.Center
        ) {
            GreenButton()
        }
    }
}