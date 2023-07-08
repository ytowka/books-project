package com.gruppa.books.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.gruppa.books.R
import com.gruppa.books.databinding.FragmentProductPageBinding

class ProductPageFragment : Fragment() {

    lateinit var binding: FragmentProductPageBinding

    val viewModel by lazy {
        ViewModelProvider(this)[ProductPageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductPageBinding.bind(view)

        val productPageAdapter = ProductPageAdapter()

        val gridLayout = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.rvProductPage.apply {
            adapter = productPageAdapter
            layoutManager = gridLayout
        }

        viewModel.reviews.observe(viewLifecycleOwner) {
            Log.e("DEBUGG", it.size.toString())
            productPageAdapter.list = it
        }

        val product = CatalogViewModel().catalog.value?.single { it.id == arguments?.getLong(ID) }
        val glide = Glide.with(this)
        binding.run {
            tvAuthor.text = product?.author
            tvAnnotation.text = product?.description
            tvName.text = product?.name
            tvEdition.text = product?.editionYear.toString()
            tvGrade.text = product?.mark.toString()
            tvPrice.text = product?.price.toString()
            glide.load(product?.imageUrl).into(ivBook)

            btnBuy.setOnClickListener {
                Log.d("debugg", "buy")
            }

            btnGoBack.setOnClickListener { findNavController().navigate(R.id.action_productPageFragment_to_catalog) }
        }

    }

    companion object {
        private const val ID = "ID"

        fun createBundle(id: Long): Bundle {
            val bundle = Bundle()
            bundle.putLong(ID, id)
            return bundle
        }

    }

}