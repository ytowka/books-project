package com.gruppa.books

import androidx.room.Room
import com.gruppa.books.data.BooksRepository
import com.gruppa.books.data.db.BooksDatabase
import com.gruppa.books.data.db.TypeConverters
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainModule(val app: App) {

    val database by lazy {
        BooksDatabase.create(app)
    }

    val booksDao by lazy {
        database.getBooksDao()
    }

    val executorService: ExecutorService by lazy { Executors.newCachedThreadPool() }

    val repository: BooksRepository by lazy {
        BooksRepository.Impl(
            booksDao,
            executorService
        )
    }
}