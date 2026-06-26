package com.example.fireextinguishinginstallationsmobile.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorSchemeOrigin = lightColorScheme(
    //primary = Purple40,
    //secondary = PurpleGrey40,
    //tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3), // Хубаво синьо
    surface = Color(0xFFF5F5F5), // Светло сиво за картите
    background = Color(0xFFFFFFFF), // Чисто бяло за фона
    onSurface = Color(0xFF1C1B1F), // Тъмен текст върху карти
    onBackground = Color(0xFF1C1B1F), // Тъмен текст върху фон
    surfaceVariant = Color(0xFFE0E0E0) // Малко по-тъмно сиво за TextField
)

@Composable
fun FireExtinguishingInstallationsMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else LightColorSchemeOrigin/*dynamicLightColorScheme(context)*/
        }

        darkTheme -> DarkColorScheme
        else -> LightColorSchemeOrigin
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}