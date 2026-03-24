package com.example.shop.article

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.article.port.`in`.ArticleUseCase
import com.example.shop.article.port.out.ArticleRepository
import com.example.shop.shared.domain.Price
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) : ArticleUseCase {

    override fun createArticle(name: String, price: Price): Article {
        val article = Article(
            id = ArticleId.generate(),
            name = name,
            price = price
        )
        return articleRepository.save(article)
    }

    override fun getArticle(articleId: ArticleId): Article? {
        return articleRepository.findById(articleId)
    }

    override fun getAllArticles(): List<Article> {
        return articleRepository.findAll()
    }
}
