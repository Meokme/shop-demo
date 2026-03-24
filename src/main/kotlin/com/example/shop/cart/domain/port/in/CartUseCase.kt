package com.example.shop.cart.domain.port.`in`

import com.example.shop.article.domain.ArticleId
import com.example.shop.cart.domain.Cart
import com.example.shop.cart.domain.CartId
import com.example.shop.shared.domain.Quantity

interface CartUseCase {
    fun createCart(): Cart
    fun addItemToCart(cartId: CartId, articleId: ArticleId, quantity: Quantity): Cart
    fun removeItemFromCart(cartId: CartId, articleId: ArticleId): Cart
    fun getCart(cartId: CartId): Cart?
}
