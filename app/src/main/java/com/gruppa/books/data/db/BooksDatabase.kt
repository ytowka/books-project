package com.gruppa.books.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.data.db.entities.ReviewEntity

@Database(
    entities = [
        BookEntity::class, BookOrderCountEntity::class, BookCartCountEntity::class, OrderEntity::class,
        ReviewEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.gruppa.books.data.db.TypeConverters::class)
abstract class BooksDatabase :  RoomDatabase(){

    abstract fun getBooksDao(): BooksDAO

    companion object{
        fun create(context: Context): BooksDatabase = Room
            .databaseBuilder(context, BooksDatabase::class.java, "booksDB")
            .createFromAsset("books.db")
            .build()
    }
}