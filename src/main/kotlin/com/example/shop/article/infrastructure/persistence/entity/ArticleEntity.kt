package com.example.shop.article.infrastructure.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "articles")
class ArticleEntity(
    @Id
    val id: UUID,
    val name: String,
    val priceAmount: java.math.BigDecimal
)
