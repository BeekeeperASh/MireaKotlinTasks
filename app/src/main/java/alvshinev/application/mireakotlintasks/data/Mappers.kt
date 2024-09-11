package alvshinev.application.mireakotlintasks.data

import alvshinev.application.mireakotlintasks.data.api.models.ArticleDTO
import alvshinev.application.mireakotlintasks.data.api.models.SourceDTO
import alvshinev.application.mireakotlintasks.data.model.Article
import alvshinev.application.mireakotlintasks.data.model.Source

fun ArticleDTO.toArticle(): Article {
    return Article(
        source = source?.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

internal fun SourceDTO.toSource(): Source {
    return Source(id = id ?: name, name = name)
}
