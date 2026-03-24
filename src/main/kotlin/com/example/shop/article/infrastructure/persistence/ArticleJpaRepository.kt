package com.example.shop.article.infrastructure.persistence

import com.example.shop.article.infrastructure.persistence.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArticleJpaRepository : JpaRepository<ArticleEntity, UUID>
