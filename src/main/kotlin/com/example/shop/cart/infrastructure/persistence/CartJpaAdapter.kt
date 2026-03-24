package com.example.shop.cart.infrastructure.persistence

import com.example.shop.article.domain.Article
import com.example.shop.article.domain.ArticleId
import com.example.shop.cart.domain.Cart
import com.example.shop.cart.domain.CartId
import com.example.shop.cart.infrastructure.persistence.entity.CartEntity
import com.example.shop.cart.infrastructure.persistence.entity.CartItemEntity
import com.example.shop.cart.port.out.CartRepository
import com.example.shop.shared.domain.Price
import com.example.shop.shared.domain.Quantity
import org.springframework.stereotype.Component
import java.util.*

@Component
class CartJpaAdapter(
    private val cartJpaRepository: CartJpaRepository
) : CartRepository {

    override fun save(cart: Cart): Cart {
        return cartJpaRepository.save(cart.toEntity()).toDomain()
    }

    override fun findById(cartId: CartId): Cart? {
        return cartJpaRepository.findById(cartId.value).orElse(null)?.toDomain()
    }

    private fun Cart.toEntity(): CartEntity {
        val entity = CartEntity(id = id.value)
        val itemEntities = getItems().map { item ->
            CartItemEntity(
                id = UUID.randomUUID(),
                articleId = item.articleId.value,
                articleName = item.articleName,
                quantity = item.quantity.value,
                unitPriceAmount = item.unitPrice.amount,
                cart = entity
            )
        }
        entity.items.addAll(itemEntities)
        return entity
    }

    private fun CartEntity.toDomain(): Cart {
        val cart = Cart(CartId(id))
        items.forEach { itemEntity ->
            cart.addItem(
                article = Article(
                    id = ArticleId(itemEntity.articleId),
                    name = itemEntity.articleName,
                    price = Price(itemEntity.unitPriceAmount)
                ),
                quantity = Quantity(itemEntity.quantity)
            )
        }
        return cart
    }
}
