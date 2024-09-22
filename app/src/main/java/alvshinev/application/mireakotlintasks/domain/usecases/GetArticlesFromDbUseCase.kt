package alvshinev.application.mireakotlintasks.domain.usecases

import alvshinev.application.mireakotlintasks.data.ArticlesRepository
import alvshinev.application.mireakotlintasks.data.RequestResult
import alvshinev.application.mireakotlintasks.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesFromDbUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    operator fun invoke(): Flow<RequestResult<List<Article>>> {
//        return repository.getArticles()
        return repository.getNewsFromDatabase()
    }

}
