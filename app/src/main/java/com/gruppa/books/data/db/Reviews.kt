package com.gruppa.books.data.db

import com.gruppa.books.data.db.entities.ReviewEntity

object Reviews {
    val testReview: ReviewEntity = ReviewEntity(
        bookId = 0,
        name = "Анатолий",
        grade = 5f,
        id = 0,
        text = "Рекомендую книгу тем кто любит мемуары. Удивительная книга об удивительном человеке. Образованный, думающий, ищущий и творящий. Я впервые поняла масштабы личности Ван Гога именно из этой переписки.\n" +
                "\n" +
                "Не пропустите!",
    )
}