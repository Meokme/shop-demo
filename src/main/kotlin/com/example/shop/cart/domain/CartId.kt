package com.example.shop.cart.domain

import java.util.UUID

@JvmInline
value class CartId(val value: UUID) {
    companion object {
        fun generate(): CartId = CartId(UUID.randomUUID())
        fun of(value: String): CartId = CartId(UUID.fromString(value))
    }
}
