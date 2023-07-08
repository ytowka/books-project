package com.gruppa.books

import android.app.Application

class App : Application(){
    val mainModule by lazy { MainModule(this) }
}