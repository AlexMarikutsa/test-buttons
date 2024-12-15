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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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


@Composable
fun BlueButton() {
    val darkBlue = Color(0xFF4A5B7C)
    val buttonShape = RoundedCornerShape(16.dp)

    val lightBlue = Color(0xFFB2CCFF)

    var isPressed by remember { mutableStateOf(false) }
    val buttonShadow = 4.dp
    val offset: Dp by animateDpAsState(
        targetValue = if (isPressed) buttonShadow else 0.dp,
        label = "Button animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp),
            painter = painterResource(id = R.drawable.image2),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isPressed.not()) darkBlue else Color(0xFF0D1A35).copy(alpha = 0.82f)
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
                if (!isPressed) {
                    for (i in 1..3) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .padding(1.dp)
                                .graphicsLayer {
                                    shadowElevation = buttonShadow.toPx()
                                    shape = buttonShape
                                    clip = false
                                }
                                .background(
                                    color = darkBlue,
                                    shape = buttonShape
                                )
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .then(
                            if (isPressed.not()) {
                                Modifier
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                lightBlue.copy(alpha = 0.3f),
                                                lightBlue.copy(alpha = 0.1f)
                                            )
                                        ),
                                        shape = buttonShape
                                    )
                                    .border(
                                        width = 1.dp,
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFFFFFFFF).copy(alpha = 0.1f),
                                                Color(0xFFFFFFFF).copy(alpha = 0.05f)
                                            )
                                        ),
                                        shape = buttonShape
                                    )
                            } else {
                                Modifier
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                lightBlue.copy(alpha = 0.15f),
                                                lightBlue.copy(alpha = 0.2f)
                                            )
                                        ),
                                        shape = buttonShape
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFF000000).copy(alpha = 0.6f),
                                        shape = buttonShape
                                    )
                            }
                        )
                        .padding(vertical = 15.dp, horizontal = 45.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.inter_medium)),
                            fontWeight = FontWeight.SemiBold,
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