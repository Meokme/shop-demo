package com.example.shop.article.infrastructure.persistence

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.article.domain.port.out.ArticleRepository
import com.example.shop.article.infrastructure.persistence.entity.ArticleEntity
import com.example.shop.shared.domain.Price
import org.springframework.stereotype.Component

@Component
class ArticlePersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleRepository {

    override fun findById(articleId: ArticleId): Article? {
        return articleJpaRepository.findById(articleId.value).orElse(null)?.toDomain()
    }

    override fun save(article: Article): Article {
        return articleJpaRepository.save(article.toEntity()).toDomain()
    }

    override fun findAll(): List<Article> {
        return articleJpaRepository.findAll().map { it.toDomain() }
    }

    private fun ArticleEntity.toDomain(): Article = Article(
        id = ArticleId(id),
        name = name,
        price = Price(priceAmount)
    )

    private fun Article.toEntity(): ArticleEntity = ArticleEntity(
        id = id.value,
        name = name,
        priceAmount = price.amount
    )
}
