package alvshinev.application.mireakotlintasks.data

import alvshinev.application.mireakotlintasks.data.model.Article

class ArticlesRepository {

    fun getArticles(): List<Article> {
        return listOf(
            Article("News about Android", "Android Android Android"),
            Article("News about IOS", "IOS IOS IOS"),
            Article("News about Windows", "Windows Windows Windows"),
            Article("News about Mac", "Mac Mac Mac"),
            Article("News about Mirea", "Mirea Mirea Mirea"),
            Article("News about Windows", "Windows Windows Windows"),
        )
    }

}
