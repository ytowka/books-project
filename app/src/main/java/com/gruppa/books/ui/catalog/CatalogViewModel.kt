package com.gruppa.books.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gruppa.books.App
import com.gruppa.books.data.BooksRepository
import com.gruppa.books.models.Book

class CatalogViewModel(private val repository: BooksRepository) : ViewModel(){
    private val books = repository.getCatalogBooks()
    private val searchQuery = MutableLiveData("")

    val catalog = MediatorLiveData<List<Book>>()

    private val catalogObserver = Observer<List<Book>>{

    }
    init {
        catalog.addSource(books){
            catalog.value = filterBooks(it, searchQuery.value ?: "")
        }
        catalog.addSource(searchQuery){
            catalog.value = filterBooks(books.value ?: emptyList(), it)
        }
    }

    fun applySearch(query: String){
        searchQuery.value = query
    }
    fun addToCart(bookId: Long){
        repository.addToCart(bookId, 1)
    }
    private fun filterBooks(list: List<Book>, query: String): List<Book>{
        return if(query.isNotBlank()){
            list.filter {
                it.name.contains(query) || it.author.contains(query)
            }
        }else list
    }

    override fun onCleared() {
        super.onCleared()
        books.removeObserver(catalogObserver)
    }

    class Factory(val repository: BooksRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CatalogViewModel(repository) as T
        }
    }
}