package com.example.shop.article.domain.port.`in`

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.shared.domain.Price

interface ArticleUseCase {
    fun createArticle(name: String, price: Price): Article
    fun getArticle(articleId: ArticleId): Article?
    fun getAllArticles(): List<Article>
}
