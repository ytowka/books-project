package com.gruppa.books.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gruppa.books.databinding.FragmentCatalogBinding
import com.gruppa.books.models.Book

class CatalogFragment : Fragment(){

    lateinit var binding: FragmentCatalogBinding

    val viewModel by lazy {
        ViewModelProvider(this)[CatalogViewModel::class.java]
    }

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
            binding.tvSearch.hideKeyboard()
            true
        }

        binding.tvSearch.addTextChangedListener { text ->
            viewModel.applySearch(text.toString())
        }


        binding.rvCatalog.apply {
            adapter = catalogAdapter
            layoutManager = gridLayout
        }

        viewModel.catalog.observe(viewLifecycleOwner){
            catalogAdapter.list = it
        }
    }
}