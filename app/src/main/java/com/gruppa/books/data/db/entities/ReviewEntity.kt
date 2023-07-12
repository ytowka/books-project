package com.gruppa.books.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruppa.books.models.Review

@Entity("reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val bookId: Long,
    val name: String,
    val grade: Float,
    val text: String,
) {
    fun toModel(): Review = Review(id, name, grade, text)
}