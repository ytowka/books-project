package com.gruppa.books.data.db.relations

import androidx.room.Embedded
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.models.Book
import com.gruppa.books.models.Order

data class OrderBooksRelation(
    @Embedded
    val bookCount: BookOrderCountEntity,

    @Embedded
    val book: BookEntity
){
    fun toModel(): Book = Book(
        id = book.id,
        name = book.name,
        imageUrl = book.imageUrl,
        author = book.author,
        editionYear = book.editionYear,
        description = book.description,
        mark = book.mark,
        price = book.price,
        bookCount.count,
    )
}