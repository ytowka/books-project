package com.gruppa.books.ui.orders

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
import com.gruppa.books.ui.app
import com.gruppa.books.ui.history.HistoryViewModel

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    val orderId by lazy { arguments?.getLong(ID) ?: 0L }
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

        app.mainModule.repository.getOrderDetails(orderId).observe(viewLifecycleOwner){
            it?.let { orderWithBooks ->
                orderAdapter.list = orderWithBooks.second
                val order = orderWithBooks.first
                binding.run {
                    tvOrder.text = binding.root.context.getString(R.string.detailsHeader, order.number)
                    val formatter = SimpleDateFormat ("dd.MM.yy")
                    tvDateValue.text = binding.root.context.getString(
                        R.string.date, formatter.format(order.date)
                    )
                    tvPrice.text = binding.root.context.getString(R.string.detailsPrice, order.totalPrice)
                    btnGoBack.setOnClickListener { findNavController().navigate(R.id.action_orderFragment_to_history) }
                }
            }
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