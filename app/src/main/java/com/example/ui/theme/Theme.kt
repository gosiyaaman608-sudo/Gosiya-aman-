package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val FlickoraDarkColorScheme = darkColorScheme(
    primary = CinemaGold,
    onPrimary = ObsidianBg,
    primaryContainer = GoldContainer,
    onPrimaryContainer = AmberGlow,
    secondary = ElectricViolet,
    onSecondary = PureSilk,
    secondaryContainer = VioletContainer,
    onSecondaryContainer = PureSilk,
    tertiary = RoseCoral,
    onTertiary = PureSilk,
    tertiaryContainer = CoralContainer,
    onTertiaryContainer = PureSilk,
    background = ObsidianBg,
    onBackground = PureSilk,
    surface = VelvetSurface,
    onSurface = PureSilk,
    surfaceVariant = VelvetElevated,
    onSurfaceVariant = MutedSlate,
    outline = VelvetStroke,
    outlineVariant = SubtleSlate,
    error = ErrorCrimson,
    onError = ObsidianBg
)

val FlickoraShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun FlickoraTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FlickoraDarkColorScheme,
        typography = FlickoraTypography,
        shapes = FlickoraShapes,
        content = content
    )
}
