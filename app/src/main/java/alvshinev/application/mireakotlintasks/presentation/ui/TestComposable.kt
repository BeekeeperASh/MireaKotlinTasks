package alvshinev.application.mireakotlintasks.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TestComposable(){
    LazyColumn {
        items(200) {
            Text(text = "Item number $it", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ))
        }
    }
}
