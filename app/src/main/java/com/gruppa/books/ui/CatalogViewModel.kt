package com.gruppa.books.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.gruppa.books.models.Book

class CatalogViewModel : ViewModel(){
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
            name = "Письма к брату Тео 11",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ), Book(
            id = 3,
            name = "Письма к брату Тео 111",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 4,
            name = "Письма к брату Тео 2",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ), Book(
            id = 5,
            name = "Письма к брату Тео 22",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ), Book(
            id = 6,
            name = "Письма к брату Тео 222",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ), Book(
            id = 7,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ), Book(
            id = 8,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        )
    )

    private val _catalog = MutableLiveData(testBooks)
    val catalog: LiveData<List<Book>> = _catalog

    private var originalCatalog = testBooks

    fun applySearch(query: String){
        if(query.isNotBlank()){
            _catalog.value = originalCatalog.filter {
                it.name.contains(query) || it.author.contains(query)
            }
        }else{
            _catalog.value = originalCatalog
        }

    }
}