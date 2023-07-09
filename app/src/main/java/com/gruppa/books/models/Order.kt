package com.gruppa.books.models

import java.util.Date

data class Order(
    val id: Int,
    val date: Date,
    val number: String,
    val quantityBooks : Int,
    val totalPrice: Int,
)
