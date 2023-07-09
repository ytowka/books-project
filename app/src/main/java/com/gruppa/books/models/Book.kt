package com.gruppa.books.models

import com.gruppa.books.data.db.entities.BookEntity

data class Book(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val author: String,
    val editionYear: Int,
    val description: String,
    val mark: Float,
    val price: Int,
    val inCartCount: Int = 0,
) {

    fun toEntity() = BookEntity(
        id, name, imageUrl, author, editionYear, description, mark, price
    )
}