package com.gruppa.books.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gruppa.books.data.BooksRepository

class ShoppingCartViewModel (val repository: BooksRepository) : ViewModel(){

    val cart = repository.getCart()

    fun makeOrder(): LiveData<Long>?{
        val cartBooks = cart.value
        if(cartBooks != null && !cartBooks.isEmpty()){
            return repository.makeOrder(cartBooks.map { book -> book.id to book.inCartCount })
        }else{
            return null
        }
    }

    class Factory(val repository: BooksRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingCartViewModel(repository) as T
        }
    }
}