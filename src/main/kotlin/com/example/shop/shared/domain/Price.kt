package com.example.shop.shared.domain

import java.math.BigDecimal

data class Price(val amount: BigDecimal) {
    init {
        require(amount >= BigDecimal.ZERO) { "Price must be positive, but was $amount" }
    }

    operator fun times(quantity: Quantity): Price = Price(amount.multiply(BigDecimal(quantity.value)))
    operator fun plus(other: Price): Price = Price(amount.add(other.amount))

    companion object {
        val ZERO = Price(BigDecimal.ZERO)

        fun of(value: Double): Price = Price(BigDecimal.valueOf(value))
    }
}
