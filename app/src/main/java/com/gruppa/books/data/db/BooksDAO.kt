package com.gruppa.books.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gruppa.books.data.db.entities.BookCartCountEntity
import com.gruppa.books.data.db.relations.BookCartCountRelation
import com.gruppa.books.data.db.entities.BookEntity
import com.gruppa.books.data.db.entities.BookOrderCountEntity
import com.gruppa.books.data.db.relations.OrderBooksRelation
import com.gruppa.books.data.db.entities.OrderEntity
import com.gruppa.books.data.db.entities.ReviewEntity
import com.gruppa.books.models.Book
import com.gruppa.books.models.Review

@Dao
interface BooksDAO {
    @Transaction
    @Query("""
        SELECT books.*, shopping_cart.* FROM books
        LEFT JOIN shopping_cart on shopping_cart.bookId = books.id
        """)
    fun getCatalogBooks(): LiveData<List<BookCartCountRelation>>

    @Query("""
        SELECT * FROM books WHERE id in(:ids)
    """)
    fun getBooks(ids: List<Long>): List<BookEntity>

    @Transaction
    @Query("""
        SELECT books.*, shopping_cart.* FROM books
        LEFT JOIN shopping_cart on shopping_cart.bookId = books.id
        WHERE count > 0
        """)
    fun getShoppingCart(): LiveData<List<BookCartCountRelation>>

    @Query("""DELETE FROM shopping_cart""")
    fun clearShoppingCart()

    @Transaction
    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookDetails(bookId: Long): LiveData<BookCartCountRelation>

    @Query("SELECT * FROM orders")
    fun getHistory(): LiveData<List<OrderEntity>>

    @Transaction
    @Query("""SELECT 
            orders.*,
            order_books_count.*,
            books.id AS 'book_id',
            books.author AS 'book_author',
            books.description AS 'book_description',
            books.editionYear AS 'book_editionYear',
            books.imageUrl AS 'book_imageUrl',
            books.mark AS 'book_mark',
            books.name AS 'book_name',
            books.price AS 'book_price'
            FROM orders
            INNER JOIN order_books_count on order_books_count.orderId = orders.id
            INNER JOIN books on books.id = order_books_count.bookId
            WHERE orders.id = :orderId
            """)
    fun getOrderDetails(orderId: Int): LiveData<List<OrderBooksRelation>>

    @Query("""SELECT * FROM reviews WHERE bookId = :bookId""")
    fun getBookReviews(bookId: Long): LiveData<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToCart(bookCartCountEntity: BookCartCountEntity)

    @Insert
    fun insertOrder(orderEntity: OrderEntity): Long

    @Insert
    fun insertReviews(reviews: List<ReviewEntity>)

    @Insert
    fun insertOrderBooks(booksOrderEntity: List<BookOrderCountEntity>)

    @Insert
    fun insertBooks(books: List<BookEntity>)
}