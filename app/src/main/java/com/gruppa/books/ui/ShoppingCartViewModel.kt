package com.gruppa.books.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppa.books.models.Book

class ShoppingCartViewModel : ViewModel() {

    private val testOrders: List<Book> = listOf(
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 1,
            name = "Письма к брату Тео 1",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 2,
            name = "Письма к брату Тео 2",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 3,
            name = "Письма к брату Тео 22",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
    )

    private val _shoppingCart = MutableLiveData(testOrders)
    val shoppingCart: LiveData<List<Book>> = _shoppingCart

    private var originalShoppingCart = testOrders
}