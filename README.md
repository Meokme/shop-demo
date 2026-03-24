# Demo Shop Application

Ein Kotlin Spring Boot Shop-Beispiel mit hexagonaler Architektur und Domain-Driven Design.

## Tech Stack

- Kotlin 2.2.21 + Spring Boot 4.0.4
- PostgreSQL + Spring Data JPA
- Gradle (Kotlin DSL)

## Projektstruktur

```
com.example.shop/
├── article/
│   ├── domain/
│   │   ├── Article.kt
│   │   └── ArticleId.kt
│   ├── port/
│   │   ├── in/ArticleUseCase.kt
│   │   └── out/ArticleRepository.kt
│   ├── ArticleService.kt
│   └── infrastructure/
│       ├── rest/
│       │   ├── ArticleController.kt
│       │   └── dto/
│       │       ├── CreateArticleRequest.kt
│       │       └── ArticleResponse.kt
│       └── persistence/
│           ├── ArticleJpaRepository.kt
│           ├── ArticlePersistenceAdapter.kt
│           └── entity/ArticleEntity.kt
├── cart/
│   ├── domain/
│   │   ├── Cart.kt
│   │   ├── CartItem.kt
│   │   └── CartId.kt
│   ├── port/
│   │   ├── in/CartUseCase.kt
│   │   └── out/CartRepository.kt
│   ├── CartService.kt
│   └── infrastructure/
│       ├── rest/
│       │   ├── CartController.kt
│       │   └── dto/
│       │       ├── AddItemRequest.kt
│       │       └── CartResponse.kt
│       └── persistence/
│           ├── CartJpaRepository.kt
│           ├── CartPersistenceAdapter.kt
│           └── entity/
│               ├── CartEntity.kt
│               └── CartItemEntity.kt
├── shared/domain/
│   ├── Quantity.kt
│   └── Price.kt
└── DemoShopApplication.kt
```

## Voraussetzungen

- Java 21
- Docker (für PostgreSQL)

## Lokale Entwicklung

```bash
# 1. PostgreSQL starten
docker compose up -d

# 2. Anwendung starten
./gradlew bootRun
```

## API Endpoints

### Articles

```bash
# Artikel erstellen
POST /api/articles
{
  "name": "Laptop",
  "price": 999.99
}

# Alle Artikel abrufen
GET /api/articles

# Einzelner Artikel
GET /api/articles/{id}
```

### Carts

```bash
# Warenkorb erstellen
POST /api/carts

# Warenkorb abrufen
GET /api/carts/{cartId}

# Artikel hinzufügen
POST /api/carts/{cartId}/items
{
  "articleId": "<article-id>",
  "quantity": 2
}

# Artikel entfernen
DELETE /api/carts/{cartId}/items/{articleId}
```

## Beispiel-Workflow

```bash
# 1. Artikel erstellen
ARTICLE_ID=$(curl -s -X POST http://localhost:8080/api/articles \
  -H "Content-Type: application/json" \
  -d '{"name": "Laptop", "price": 999.99}' | jq -r '.id')

# 2. Warenkorb erstellen
CART_ID=$(curl -s -X POST http://localhost:8080/api/carts | jq -r '.cartId')

# 3. Artikel hinzufügen
curl -s -X POST "http://localhost:8080/api/carts/$CART_ID/items" \
  -H "Content-Type: application/json" \
  -d "{\"articleId\": \"$ARTICLE_ID\", \"quantity\": 2}"

# 4. Warenkorb abrufen
curl http://localhost:8080/api/carts/$CART_ID
```
