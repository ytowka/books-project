package com.gruppa.books.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gruppa.books.databinding.FragmentCatalogBinding
import com.gruppa.books.models.Book

class CatalogFragment : Fragment(){

    lateinit var binding: FragmentCatalogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogAdapter = CatalogAdapter(
            onCardClick = {
                Log.d("debugg", "onCardClick() called $it")
            },
            onBookBuy = {
                Log.d("debugg", "onBookBuy() called $it")
            }
        )


        val gridLayout = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.tvSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                Log.d(
                    "debugg",
                    "onViewCreated() called with: v = $v, actionId = $actionId, event = $event"
                )
            }
            binding.tvSearch.hideKeyboard()
            true
        }


        binding.rvCatalog.apply {
            adapter = catalogAdapter
            layoutManager = gridLayout
        }

        catalogAdapter.list = testBooks
    }

    val testBooks: List<Book> = listOf(
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
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 2,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),Book(
            id = 3,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),
        Book(
            id = 4,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),Book(
            id = 5,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),Book(
            id = 6,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),Book(
            id = 7,
            name = "Письма к брату Тео",
            imageUrl = "https://cdn.ast.ru/v2/ASE000000000833774/COVER/cover1__w600.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2004,
            description = "",
            mark = 5f,
            price = 240,
        ),Book(
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
}