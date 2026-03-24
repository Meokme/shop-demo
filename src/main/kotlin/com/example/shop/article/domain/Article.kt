package com.example.shop.article.domain

import com.example.shop.shared.domain.Price

data class Article(
    val id: ArticleId,
    val name: String,
    val price: Price
)
