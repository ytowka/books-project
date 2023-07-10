package com.gruppa.books.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gruppa.books.data.BooksRepository

class ShoppingCartViewModel (val repository: BooksRepository) : ViewModel(){

    val cart = repository.getCart()

    fun makeOrder(){
        cart.value?.let { cart ->
            repository.makeOrder(cart.map { book -> book.id to book.inCartCount })
        }
    }

    class Factory(val repository: BooksRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingCartViewModel(repository) as T
        }
    }
}