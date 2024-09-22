package alvshinev.application.mireakotlintasks.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun LaunchingImage(){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var input by rememberSaveable {
            mutableStateOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPh0plnJJIz9HuHiZJoru2ZHn54pkD01e-1Q&s")
        }
        OutlinedTextField(
            modifier = Modifier,
            value = input,
            onValueChange = {
                input = it
            },
            label = {
                Text(text = "Input image url")
            }
        )
        AsyncImage(
            modifier = Modifier.size(256.dp).padding(top = 20.dp),
            model = input,
            contentDescription = "Launching image",
            contentScale = ContentScale.Fit
        )
    }
}
