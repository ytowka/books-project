package com.gruppa.books.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppa.books.App
import com.gruppa.books.models.Book

class ShoppingCartViewModel : ViewModel() {


    val shoppingCart: LiveData<List<Book>> = App.app.mainModule.repository.getCart()

}