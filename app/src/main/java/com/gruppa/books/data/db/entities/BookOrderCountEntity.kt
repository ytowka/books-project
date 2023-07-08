package com.gruppa.books.data.db.entities

import androidx.room.Entity

@Entity(tableName = "order_books_count", primaryKeys = ["orderId", "bookId"])
data class BookOrderCountEntity(
    val orderId: Int,
    val bookId: Long,
    val count: Int,
)
