package com.example.shop.cart.infrastructure.rest

import com.example.shop.article.domain.ArticleId
import com.example.shop.cart.domain.Cart
import com.example.shop.cart.domain.CartId
import com.example.shop.cart.infrastructure.rest.dto.AddItemRequest
import com.example.shop.cart.infrastructure.rest.dto.CartItemResponse
import com.example.shop.cart.infrastructure.rest.dto.CartResponse
import com.example.shop.cart.port.`in`.CartUseCase
import com.example.shop.shared.domain.Quantity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/carts")
class CartController(
    private val cartService: CartUseCase
) {

    @PostMapping
    fun createCart(): ResponseEntity<CartResponse> {
        val cart = cartService.createCart()
        return ResponseEntity.ok(cart.toResponse())
    }

    @GetMapping("/{cartId}")
    fun getCart(@PathVariable cartId: String): ResponseEntity<CartResponse> {
        val cart = cartService.getCart(CartId.of(cartId))
        return cart?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/{cartId}/items")
    fun addItem(
        @PathVariable cartId: String,
        @RequestBody request: AddItemRequest
    ): ResponseEntity<CartResponse> {
        val cart = cartService.addItemToCart(
            cartId = CartId.of(cartId),
            articleId = ArticleId.of(request.articleId),
            quantity = Quantity(request.quantity)
        )
        return ResponseEntity.ok(cart.toResponse())
    }

    @DeleteMapping("/{cartId}/items/{articleId}")
    fun removeItem(
        @PathVariable cartId: String,
        @PathVariable articleId: String
    ): ResponseEntity<CartResponse> {
        val cart = cartService.removeItemFromCart(
            cartId = CartId.of(cartId),
            articleId = ArticleId.of(articleId)
        )
        return ResponseEntity.ok(cart.toResponse())
    }

    private fun Cart.toResponse(): CartResponse {
        return CartResponse(
            cartId = id.value.toString(),
            items = getItems().map { item ->
                CartItemResponse(
                    articleId = item.articleId.value.toString(),
                    articleName = item.articleName,
                    quantity = item.quantity.value,
                    unitPrice = item.unitPrice.amount,
                    totalPrice = item.totalPrice.amount
                )
            },
            totalPrice = totalPrice().amount
        )
    }
}
