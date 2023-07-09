package com.gruppa.books.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gruppa.books.data.db.BooksDAO
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.data.db.relations.BookCartCountRelation
import com.gruppa.books.data.db.relations.toOrderWithBooks
import com.gruppa.books.models.Book
import com.gruppa.books.models.Order
import com.gruppa.books.models.Review
import java.util.Date
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.ExecutorService

interface BooksRepository {

    fun getCatalogBooks(): LiveData<List<Book>>
    fun getBookDetails(bookId: Long): LiveData<Book>
    fun getHistory(): LiveData<List<Order>>
    fun getCart(): LiveData<List<Book>>
    fun getOrderDetails(orderId: Int): LiveData<Pair<Order, List<Book>>?>
    fun addToCart(bookId: Long, count: Int)
    fun makeOrder(booksIds: List<Pair<Long, Int>>)
    fun getBookReviews(bookId: Long): LiveData<List<Review>>

    class Impl(
        val booksDAO: BooksDAO,
        val executorService: ExecutorService
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

        override fun getOrderDetails(orderId: Int): LiveData<Pair<Order, List<Book>>?> {
            return booksDAO.getOrderDetails(orderId).map { orderDetails ->
                orderDetails.toOrderWithBooks()
            }
        }

        override fun addToCart(bookId: Long, count: Int) {
            executorService.submit {
                booksDAO.addToCart(BookCartCountEntity(bookId, count))
            }
        }

        override fun makeOrder(booksIds: List<Pair<Long, Int>>) {
            executorService.submit {
                val books = booksDAO.getBooks(booksIds.map {
                    it.first
                })
                val orderEntity = OrderEntity(
                    date = Date(),
                    quantityBooks = books.size,
                    totalPrice = books.sumOf { it.price },
                )
                val orderId = booksDAO.insertOrder(orderEntity)
                val orderBookEntities = books.mapIndexed { index: Int, bookEntity: BookEntity ->
                    BookOrderCountEntity(
                        orderId = orderId,
                        bookId = bookEntity.id,
                        count = booksIds[index].second
                    )
                }
                booksDAO.insertOrderBooks(orderBookEntities)
            }
        }

        override fun getBookReviews(bookId: Long): LiveData<List<Review>> {
            return booksDAO.getBookReviews(bookId).map { reviewEntities ->
                reviewEntities.map { it.toModel() }
            }
        }

    }
}