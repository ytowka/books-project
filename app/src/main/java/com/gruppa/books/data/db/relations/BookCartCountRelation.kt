package com.gruppa.books.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.models.Book

data class BookCartCountRelation(

    @Embedded
    val book: BookEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "bookId",
    )
    val count: BookCartCountEntity
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
        inCartCount = count.count
    )
}
