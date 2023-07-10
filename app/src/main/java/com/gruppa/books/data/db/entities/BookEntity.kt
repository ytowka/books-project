package com.gruppa.books.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruppa.books.models.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val imageUrl: String,
    val author: String,
    val editionYear: Int,
    val description: String,
    val mark: Float,
    val price: Int,
) {
    fun toModel(): Book = Book(
        id, name, imageUrl, author, editionYear, description, mark, price, 0
    )
}