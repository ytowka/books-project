package com.gruppa.books.ui.orders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gruppa.books.R
import com.gruppa.books.databinding.ItemOrderDetailBinding
import com.gruppa.books.models.Book

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var list: List<Book> = emptyList()
        set(value) {
            Log.e("DEBUGG", value.size.toString())
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemOrderDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OrderViewHolder(val binding: ItemOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.tvName.text = book.name
            binding.tvCount.text =
                binding.root.context.getString(R.string.quantity, book.inCartCount)
        }
    }
}