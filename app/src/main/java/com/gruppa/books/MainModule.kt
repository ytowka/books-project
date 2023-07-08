package com.gruppa.books

import androidx.room.Room
import androidx.room.RoomDatabase
import com.gruppa.books.data.db.BooksDatabase
import com.gruppa.books.data.db.TypeConverters

class MainModule(val app: App) {

    val database by lazy {
        Room
            .databaseBuilder(app, BooksDatabase::class.java, "booksDB")
            .addTypeConverter(TypeConverters::class)
            .build()
    }

    val booksDao by lazy {
        database.getBooksDao()
    }
}