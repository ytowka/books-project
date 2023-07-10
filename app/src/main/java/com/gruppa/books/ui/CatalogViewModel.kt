package com.gruppa.books.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.gruppa.books.App
import com.gruppa.books.models.Book

class CatalogViewModel : ViewModel(){


    private val _catalog = MutableLiveData(listOf<Book>())
    val catalog: LiveData<List<Book>> = App.app.mainModule.repository.getCatalogBooks()

    private var originalCatalog = listOf<Book>()

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