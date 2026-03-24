package com.example.shop.cart.domain

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.shared.domain.Price
import com.example.shop.shared.domain.Quantity

class Cart(
    val id: CartId,
    private val items: MutableList<CartItem> = mutableListOf()
) {
    fun addItem(article: Article, quantity: Quantity) {
        val existingItem = items.find { it.articleId == article.id }
        if (existingItem != null) {
            val newQuantity = existingItem.quantity + quantity
            items.remove(existingItem)
            items.add(existingItem.copy(quantity = newQuantity))
        } else {
            items.add(
                CartItem(
                    articleId = article.id,
                    articleName = article.name,
                    quantity = quantity,
                    unitPrice = article.price
                )
            )
        }
    }

    fun removeItem(articleId: ArticleId) {
        val removed = items.removeIf { it.articleId == articleId }
        if (!removed) {
            throw IllegalArgumentException("Article not found in cart: $articleId")
        }
    }

    fun getItems(): List<CartItem> = items.toList()

    fun totalPrice(): Price = items.fold(Price.ZERO) { acc, item -> acc + item.totalPrice }

    companion object {
        fun create(): Cart = Cart(CartId.generate())
    }
}
