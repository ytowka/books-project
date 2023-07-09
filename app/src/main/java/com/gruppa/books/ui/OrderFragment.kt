package com.gruppa.books.ui

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gruppa.books.R
import com.gruppa.books.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[OrderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderAdapter = OrderAdapter()

        val gridLayout = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        binding.rvItems.apply {
            adapter = orderAdapter
            layoutManager = gridLayout
        }

        viewModel.books.observe(viewLifecycleOwner) {
            Log.e("DEBUGG", it.size.toString())
            orderAdapter.list = it
        }

        val order = HistoryViewModel().history.value?.single { it.id == arguments?.getInt(ID) }
        binding.run {
            tvOrder.text = binding.root.context.getString(R.string.detailsHeader, order?.number)
            val formatter = SimpleDateFormat ("dd.MM.yy")
            tvDateValue.text = binding.root.context.getString(
                R.string.date, formatter.format(order?.date)
            )
            tvPrice.text = binding.root.context.getString(R.string.detailsPrice, order?.totalPrice)
            btnGoBack.setOnClickListener { findNavController().navigate(R.id.action_orderFragment_to_history) }
        }
    }

    companion object {
        private const val ID = "ID"

        fun createBundle(id: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ID, id)
            return bundle
        }

    }
}