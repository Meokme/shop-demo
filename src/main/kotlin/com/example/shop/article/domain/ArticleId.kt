package com.example.shop.article.domain

import java.util.UUID

@JvmInline
value class ArticleId(val value: UUID) {
    companion object {
        fun generate(): ArticleId = ArticleId(UUID.randomUUID())
        fun of(value: String): ArticleId = ArticleId(UUID.fromString(value))
    }
}
