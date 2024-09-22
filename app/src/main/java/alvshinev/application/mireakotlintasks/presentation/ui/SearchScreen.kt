package alvshinev.application.mireakotlintasks.presentation.ui

import alvshinev.application.mireakotlintasks.presentation.ui_logic.MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun SearchScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var input by rememberSaveable {
            mutableStateOf(viewModel.getRequestFromPrefs(context))
        }
        OutlinedTextField(
            modifier = Modifier.align(Alignment.TopCenter),
            value = input,
            onValueChange = {
                input = it
            },
            label = {
                Text(text = "Input your query")
            }
        )
        Button(onClick = {
            viewModel.loadNewsFromApi(input)
            viewModel.saveRequestToPrefs(input, context)
        }) {
            Text(text = "Search news")
        }
    }

}
