package alvshinev.application.mireakotlintasks.presentation.ui_logic

import alvshinev.application.mireakotlintasks.data.RequestResult
import alvshinev.application.mireakotlintasks.data.model.Article
import kotlinx.collections.immutable.toImmutableList

internal fun RequestResult<List<Article>>.toState(): State {
    return when (this) {
        is RequestResult.Error -> State.Error(data?.toImmutableList())
        is RequestResult.InProgress -> State.Loading(data?.toImmutableList())
        is RequestResult.Success -> State.Success(data.toImmutableList())
    }
}
