package com.gruppa.books.data.db.relations

import androidx.room.Embedded
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.models.Book
import com.gruppa.books.models.Order

data class OrderBooksRelation(
    @Embedded
    val orderEntity: OrderEntity,

    @Embedded
    val bookCount: BookOrderCountEntity,

    @Embedded("book_")
    val book: BookEntity
)

fun List<OrderBooksRelation>.toOrderWithBooks(): Pair<Order, List<Book>>? {
    return firstOrNull()?.let { relation ->
        relation.orderEntity.toModel() to map {
            Book(
                id = it.book.id,
                name = it.book.name,
                imageUrl = it.book.imageUrl,
                author = it.book.author,
                editionYear = it.book.editionYear,
                description = it.book.description,
                mark = it.book.mark,
                price = it.book.price,
                it.bookCount.count,
            )
        }
    }
}