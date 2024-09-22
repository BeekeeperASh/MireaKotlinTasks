package alvshinev.application.mireakotlintasks.presentation.ui

import alvshinev.application.mireakotlintasks.presentation.uikit.AppTheme
import alvshinev.application.mireakotlintasks.presentation.uikit.Dimensions
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ComposeSample() {
    MaterialTheme (
        typography = AppTheme.typography,
        colorScheme = AppTheme.colorScheme
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Card (
                modifier = Modifier.padding(Dimensions.largePadding).align(Alignment.Center),
                shape = CardDefaults.elevatedShape,
                elevation = CardDefaults.cardElevation(Dimensions.cardElevation),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                border = BorderStroke(Dimensions.borderStroke, MaterialTheme.colorScheme.onSurface)
            ) {
                Column () {
                    Text(
                        modifier = Modifier.padding(Dimensions.mediumPadding),
                        text = "Шинёв А.В.",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        modifier = Modifier.padding(Dimensions.smallPadding),
                        text = "ИКБО-06-22",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

    }
}
