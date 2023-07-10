package com.gruppa.books.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppa.books.models.Book

class OrderViewModel : ViewModel() {
    private val testBooks: List<Book> = listOf(
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
        Book(
            id = 0,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
            inCartCount = 15,
        ),
    )

    private val _books = MutableLiveData(testBooks)
    val books: LiveData<List<Book>> = _books
}