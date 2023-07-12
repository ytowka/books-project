package com.gruppa.books

import android.app.Application
import com.gruppa.books.data.db.Books

class App : Application(){
    val mainModule by lazy { MainModule(this) }

    override fun onCreate() {
        super.onCreate()

        /*Thread{
            val ids = mainModule.booksDao.insertBooks(Books.testBooks.map { it.toEntity() })
            val reviews = ids.map {
                Reviews.testReview.copy(bookId = it)
            }
            mainModule.booksDao.insertReviews(reviews)
        }.start()*/

    }
}