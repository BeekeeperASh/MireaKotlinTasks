package alvshinev.application.mireakotlintasks.presentation.ui_logic

import alvshinev.application.mireakotlintasks.data.model.Article
import kotlinx.collections.immutable.ImmutableList

public sealed class State(public open val articles: ImmutableList<Article>?) {

    public data object None : State(articles = null)

    public class Loading(articles: ImmutableList<Article>? = null) : State(articles)

    public class Error(articles: ImmutableList<Article>? = null) : State(articles)

    public class Success(override val articles: ImmutableList<Article>) : State(articles)
}
