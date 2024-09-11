package alvshinev.application.mireakotlintasks.presentation.ui

import alvshinev.application.mireakotlintasks.R
import alvshinev.application.mireakotlintasks.presentation.ui_logic.MainViewModel
import alvshinev.application.mireakotlintasks.presentation.ui_logic.State
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun NewsScreen(
    viewModel: MainViewModel
){
    val screenState by viewModel.state.collectAsState()
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.news_screen))
        when(screenState){
            is State.Loading -> {
                CircularProgressIndicator()
            }
            is State.Error -> {
                Text(text = "Error", color = Color.Red)
            }
            State.None -> {
                Text(text = "There is no articles", color = Color.Red)
            }
            is State.Success -> {
                LazyColumn (modifier = Modifier.fillMaxSize()) {
                    items(screenState.articles ?: emptyList()) { article ->
                        Column (modifier = Modifier.padding(20.dp)) {
                            Text(text = article.title ?: " ")
                            Text(text = article.description ?: " ")
                        }
                    }
                }
            }
        }

    }

}
