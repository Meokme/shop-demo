package com.example.shop.cart

import com.example.shop.article.domain.ArticleId
import com.example.shop.article.port.out.ArticleRepository
import com.example.shop.cart.domain.Cart
import com.example.shop.cart.domain.CartId
import com.example.shop.cart.port.`in`.CartUseCase
import com.example.shop.cart.port.out.CartRepository
import com.example.shop.shared.domain.Quantity
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val articleRepository: ArticleRepository
) : CartUseCase {

    override fun createCart(): Cart {
        val cart = Cart.create()
        return cartRepository.save(cart)
    }

    override fun addItemToCart(cartId: CartId, articleId: ArticleId, quantity: Quantity): Cart {
        val cart = cartRepository.findByIdOrThrow(cartId)
        val article = articleRepository.findByIdOrThrow(articleId)
        cart.addItem(article, quantity)
        return cartRepository.save(cart)
    }

    override fun removeItemFromCart(cartId: CartId, articleId: ArticleId): Cart {
        val cart = cartRepository.findByIdOrThrow(cartId)
        cart.removeItem(articleId)
        return cartRepository.save(cart)
    }

    override fun getCart(cartId: CartId): Cart? {
        return cartRepository.findById(cartId)
    }
}
