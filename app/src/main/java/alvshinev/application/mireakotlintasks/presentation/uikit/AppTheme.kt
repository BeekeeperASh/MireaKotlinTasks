package alvshinev.application.mireakotlintasks.presentation.uikit

import alvshinev.application.mireakotlintasks.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppTheme {

    val colorScheme = lightColorScheme(
        primary = Color(0xFF4CAF50),
        onPrimary = Color.White,
        secondary = Color(0xFFFFEB3B),
        background = Color(0xFFF6F6F6),
        surface = Color.White,
        onSurface = Color.Black
    )

    val typography = Typography(
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Black
        ),
        titleLarge = TextStyle(
            fontSize = 32.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )
    )

}

object Dimensions {
    val smallPadding = 8.dp
    val mediumPadding = 16.dp
    val largePadding = 24.dp
    val cardElevation = 8.dp
    val borderStroke = 2.dp
}
