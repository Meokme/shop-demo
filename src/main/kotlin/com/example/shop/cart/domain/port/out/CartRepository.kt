package com.example.shop.cart.domain.port.out

import com.example.shop.cart.domain.Cart
import com.example.shop.cart.domain.CartId

interface CartRepository {
    fun save(cart: Cart): Cart
    fun findById(cartId: CartId): Cart?
    fun findByIdOrThrow(cartId: CartId): Cart = findById(cartId)
        ?: throw IllegalArgumentException("Cart not found: $cartId")
}
