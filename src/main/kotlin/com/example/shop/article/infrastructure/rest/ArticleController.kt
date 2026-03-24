package com.example.shop.article.infrastructure.rest

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.article.infrastructure.rest.dto.ArticleResponse
import com.example.shop.article.infrastructure.rest.dto.CreateArticleRequest
import com.example.shop.article.port.`in`.ArticleUseCase
import com.example.shop.shared.domain.Price
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/articles")
class ArticleController(
    private val articleService: ArticleUseCase
) {

    @PostMapping
    fun createArticle(@RequestBody request: CreateArticleRequest): ResponseEntity<ArticleResponse> {
        val article = articleService.createArticle(
            request.name,
            Price.of(request.price)
        )
        return ResponseEntity.ok(article.toResponse())
    }

    @GetMapping
    fun getAllArticles(): ResponseEntity<List<ArticleResponse>> {
        val articles = articleService.getAllArticles()
        return ResponseEntity.ok(articles.map { it.toResponse() })
    }

    @GetMapping("/{articleId}")
    fun getArticle(@PathVariable articleId: String): ResponseEntity<ArticleResponse> {
        val article = articleService.getArticle(ArticleId.of(articleId))
        return article?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()
    }

    private fun Article.toResponse(): ArticleResponse {
        return ArticleResponse(
            id = id.value.toString(),
            name = name,
            price = price.amount
        )
    }
}
