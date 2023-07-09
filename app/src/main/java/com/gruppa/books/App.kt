package com.gruppa.books

import android.app.Application
import com.gruppa.books.data.db.Books

class App : Application(){
    val mainModule by lazy { MainModule(this) }

    override fun onCreate() {
        super.onCreate()

        /*Thread{
            mainModule.booksDao.insertBooks(Books.testBooks.map { it.toEntity() })
        }.start()*/
        app = this
    }

    companion object{
        lateinit var app: App
    }
}