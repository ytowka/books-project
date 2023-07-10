package com.gruppa.books.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.gruppa.books.data.db.BooksDAO
import com.gruppa.books.data.db.BooksDatabase
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.data.db.relations.BookCartCountRelation
import com.gruppa.books.data.db.relations.OrderBooksRelation
import com.gruppa.books.models.Book
import com.gruppa.books.models.Order
import com.gruppa.books.models.Review
import java.util.Date
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

interface BooksRepository {

    fun getCatalogBooks(): LiveData<List<Book>>
    fun getBookDetails(bookId: Long): LiveData<Book>
    fun getHistory(): LiveData<List<Order>>
    fun getCart(): LiveData<List<Book>>
    fun getOrderDetails(orderId: Long): LiveData<Pair<Order, List<Book>>>
    fun addToCart(bookId: Long, count: Int)
    fun makeOrder(booksIds: List<Pair<Long, Int>>): LiveData<Long>
    fun getBookReviews(bookId: Long): LiveData<List<Review>>

    class Impl(
        val booksDAO: BooksDAO,
        val database: BooksDatabase,
        val executorService: ExecutorService,
        ) : BooksRepository{
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
            return booksDAO.getHistory().map { orderEntities ->
                orderEntities.map(OrderEntity::toModel)
            }
        }

        override fun getCart(): LiveData<List<Book>> {
            return booksDAO.getShoppingCart().map { cart ->
                cart.map { it.toModel() }
            }
        }

        override fun getOrderDetails(orderId: Long): LiveData<Pair<Order, List<Book>>> {
            val liveData = MutableLiveData<Pair<Order, List<Book>>>()
            executorService.submit {
                val order = booksDAO.getOrderById(orderId).toModel()
                val books = booksDAO.getOrderBooks(orderId).map(OrderBooksRelation::toModel)

                liveData.postValue(order to books)
            }
            return liveData
        }

        override fun addToCart(bookId: Long, count: Int) {
            executorService.submit {
                booksDAO.addToCart(BookCartCountEntity(bookId, count.coerceIn(0..30)))
            }
        }

        override fun makeOrder(booksIds: List<Pair<Long, Int>>): LiveData<Long>{
            val liveData = MutableLiveData<Long>()
            executorService.submit {
                var orderId = 0L
                database.runInTransaction {
                    val books = booksDAO.getBooks(booksIds.map {
                        it.first
                    })
                    val orderEntity = OrderEntity(
                        date = Date(),
                        quantityBooks = books.size,
                        totalPrice = books.sumOf { it.price },
                    )
                    orderId = booksDAO.insertOrder(orderEntity)
                    val orderBookEntities = books.mapIndexed { index: Int, bookEntity: BookEntity ->
                        BookOrderCountEntity(
                            orderId = orderId,
                            bookId = bookEntity.id,
                            count = booksIds[index].second
                        )
                    }
                    booksDAO.insertOrderBooks(orderBookEntities)
                    booksDAO.clearShoppingCart()
                }
                liveData.postValue(orderId)
            }
            return liveData
        }

        override fun getBookReviews(bookId: Long): LiveData<List<Review>> {
            return booksDAO.getBookReviews(bookId).map { reviewEntities ->
                reviewEntities.map { it.toModel() }
            }
        }

    }
}