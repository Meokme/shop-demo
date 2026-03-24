package com.example.shop.article.infrastructure.rest.dto

import java.math.BigDecimal

data class ArticleResponse(
    val id: String,
    val name: String,
    val price: BigDecimal
)
