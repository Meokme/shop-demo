package com.example.shop.cart.domain

import com.example.shop.article.domain.ArticleId
import com.example.shop.shared.domain.Price
import com.example.shop.shared.domain.Quantity

data class CartItem(
    val articleId: ArticleId,
    val articleName: String,
    val quantity: Quantity,
    val unitPrice: Price
) {
    val totalPrice: Price get() = unitPrice * quantity
}
