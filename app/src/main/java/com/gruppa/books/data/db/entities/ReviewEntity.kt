package com.gruppa.books.data.db.entities

import androidx.room.Entity

@Entity("reviews")
data class ReviewEntity(
    val id: Long,
) {
}