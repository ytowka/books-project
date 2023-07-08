package com.gruppa.books.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity

data class BookOrderCountRelation(
    @Embedded
    val bookEntity: BookEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val books: BookOrderCountEntity
){
}