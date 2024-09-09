package alvshinev.application.mireakotlintasks.domain

import alvshinev.application.mireakotlintasks.data.ArticlesRepository
import alvshinev.application.mireakotlintasks.data.model.Article

class GetArticlesUseCase(
    private val repository: ArticlesRepository
) {

    operator fun invoke(): List<Article> {
        return repository.getArticles()
    }

}
