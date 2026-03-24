package com.example.shop.cart.infrastructure.persistence

import com.example.shop.cart.infrastructure.persistence.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartJpaRepository : JpaRepository<CartEntity, UUID>
