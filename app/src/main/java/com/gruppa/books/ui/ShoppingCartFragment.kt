package com.gruppa.books.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gruppa.books.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartBinding

    val viewModel by lazy {
        ViewModelProvider(this)[ShoppingCartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shoppingCartAdapter = ShoppingCartAdapter(
            onLeftBtnClick = {
                Log.d("test", "onLeftBtnClick $it")
            },
            onRightBtnClick = {
                Log.d("test", "onRightBtnClick $it")
            }
        )

        val gridLayout = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.rvShoppingCart.apply {
            adapter = shoppingCartAdapter
            layoutManager = gridLayout
        }

        viewModel.shoppingCart.observe(viewLifecycleOwner) {
            shoppingCartAdapter.list = it
        }

        binding.btnGoBack.setOnClickListener {
            Log.d(  "test", "btnGoBack")
        }

        binding.btnOrdering.setOnClickListener {
            Log.d(  "test", "btnOrdering")
        }
    }
}