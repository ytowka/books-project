package com.gruppa.books.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: Date,
    val number: String,
    val quantityBooks : Int,
    val totalPrice: Int
)
