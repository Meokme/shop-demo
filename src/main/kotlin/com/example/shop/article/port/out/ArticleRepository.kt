package com.example.shop.article.port.out

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId

interface ArticleRepository {
    fun findById(articleId: ArticleId): Article?
    fun findByIdOrThrow(articleId: ArticleId): Article = findById(articleId)
        ?: throw IllegalArgumentException("Article not found: $articleId")

    fun save(article: Article): Article
    fun findAll(): List<Article>
}
