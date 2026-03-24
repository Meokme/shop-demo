package com.example.shop.shared.domain

@JvmInline
value class Quantity(val value: Int) {
    init {
        require(value > 0) { "Quantity must be > 0" }
    }

    operator fun plus(other: Quantity): Quantity = Quantity(value + other.value)
}
