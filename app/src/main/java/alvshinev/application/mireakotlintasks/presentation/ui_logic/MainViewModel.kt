package alvshinev.application.mireakotlintasks.presentation.ui_logic

import alvshinev.application.mireakotlintasks.data.ArticlesRepository
import alvshinev.application.mireakotlintasks.data.model.Article
import alvshinev.application.mireakotlintasks.domain.GetArticlesUseCase
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel (
    private val getArticlesUseCase: GetArticlesUseCase = GetArticlesUseCase(ArticlesRepository()),
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(listOf())
    val articles: StateFlow<List<Article>> = _articles

    fun loadNews() {
        _articles.value = getArticlesUseCase.invoke()
    }

}
