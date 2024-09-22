package alvshinev.application.mireakotlintasks.domain.usecases

import alvshinev.application.mireakotlintasks.data.ArticlesRepository
import javax.inject.Inject

class ClearArticlesFromDatabaseUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke() {
        repository.clearNewsDatabase()
    }

}
