package com.gruppa.books.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shopping_cart")
data class BookCartCountEntity(
    @PrimaryKey(autoGenerate = true) val bookId: Long,
    val count: Int,
)