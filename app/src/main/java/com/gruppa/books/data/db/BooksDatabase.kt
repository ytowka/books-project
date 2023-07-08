package com.gruppa.books.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity

@Database(
    entities = [BookEntity::class, BookOrderCountEntity::class, BookCartCountEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.gruppa.books.data.db.TypeConverters::class)
abstract class BooksDatabase :  RoomDatabase(){

    abstract fun getBooksDao(): BooksDAO
}