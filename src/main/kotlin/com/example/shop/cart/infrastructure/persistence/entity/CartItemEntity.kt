package com.example.shop.cart.infrastructure.persistence.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "cart_items")
class CartItemEntity(
    @Id
    val id: UUID,
    val articleId: UUID,
    val articleName: String,
    val quantity: Int,
    val unitPriceAmount: BigDecimal,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    val cart: CartEntity? = null
)
