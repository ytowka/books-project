package com.gruppa.books.models

data class Book(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val author: String,
    val editionYear: Int,
    val description: String,
    val mark: Float,
    val price: Int,
) {
}