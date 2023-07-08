package com.gruppa.books.data.db.relations

import androidx.room.Embedded
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity

data class OrderBooksRelation(
    @Embedded("order")
    val orderEntity: OrderEntity,


    @Embedded("book_count")
    val bookCount: BookOrderCountEntity,

    @Embedded(prefix = "book_")
    val book: BookEntity
)