package com.gruppa.books.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gruppa.books.data.db.BooksDAO
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.relations.BookCartCountRelation
import com.gruppa.books.models.Book
import com.gruppa.books.models.Order

interface BooksRepository {

    fun getCatalogBooks(): LiveData<List<Book>>

    fun getBookDetails(bookId: Long): LiveData<Book>

    fun getHistory(): LiveData<List<Order>>

    fun getCart(): LiveData<List<Book>>

    fun getOrderDetails(orderId: Int): LiveData<Order>

    fun addToCart(bookId: Long, count: Long)

    fun makeOrder(books: List<Pair<Long, Int>>)

    class Impl(val booksDAO: BooksDAO) : BooksRepository{
        override fun getCatalogBooks(): LiveData<List<Book>> {
            return booksDAO.getCatalogBooks().map { bookEntities ->
                bookEntities.map(BookCartCountRelation::toModel)
            }
        }

        override fun getBookDetails(bookId: Long): LiveData<Book> {
            return booksDAO.getBookDetails(bookId).map {
                it.toModel()
            }
        }

        override fun getHistory(): LiveData<List<Order>> {
            TODO("Not yet implemented")
        }

        override fun getCart(): LiveData<List<Book>> {
            TODO("Not yet implemented")
        }

        override fun getOrderDetails(orderId: Int): LiveData<Order> {
            TODO("Not yet implemented")
        }

        override fun addToCart(bookId: Long, count: Long) {
            TODO("Not yet implemented")
        }

        override fun makeOrder(books: List<Pair<Long, Int>>) {
            TODO("Not yet implemented")
        }

    }
}