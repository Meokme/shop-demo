package com.example.shop.cart.infrastructure.rest.dto

data class AddItemRequest(
    val articleId: String,
    val quantity: Int
)
