package com.gruppa.books.ui

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruppa.books.R
import com.gruppa.books.databinding.ItemHistoryBinding
import com.gruppa.books.models.Order

class HistoryAdapter(
    val onCardClick: (Int) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var list: List<Order> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HistoryViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {

            val formatter = SimpleDateFormat ("dd.MM.yy")
            binding.tvDate.text = binding.root.context.getString(
                R.string.date, formatter.format(order.date)
            )
            binding.tvNumber.text = binding.root.context.getString(R.string.number, order.number)
            binding.tvQuantity.text =
                binding.root.context.getString(R.string.quantity, order.quantityBooks)
            binding.tvTotalPrice.text =
                binding.root.context.getString(R.string.price, order.totalPrice)

            binding.root.setOnClickListener {
                onCardClick(order.id)
            }
        }
    }

}