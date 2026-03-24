package com.example.shop.cart.infrastructure.rest.dto

import java.math.BigDecimal

data class CartResponse(
    val cartId: String,
    val items: List<CartItemResponse>,
    val totalPrice: BigDecimal
)

data class CartItemResponse(
    val articleId: String,
    val articleName: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val totalPrice: BigDecimal
)
