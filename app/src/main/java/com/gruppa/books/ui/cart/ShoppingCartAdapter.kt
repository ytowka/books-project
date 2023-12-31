package com.gruppa.books.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruppa.books.R
import com.gruppa.books.databinding.ItemShoppingCartBookBinding
import com.gruppa.books.models.Book

class ShoppingCartAdapter(
    val onCountUpdate: (Long, Int) ->  Unit,
    val onCardClick: (Long) -> Unit,
) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    var list: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        return ShoppingCartViewHolder(
            ItemShoppingCartBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShoppingCartViewHolder(val binding: ItemShoppingCartBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {

            Glide
                .with(binding.root.context)
                .load(book.imageUrl)
                .into(binding.ivBook)

            binding.llCounter.btnLeft.setOnClickListener {
                onCountUpdate(book.id, book.inCartCount-1)
            }

            binding.root.setOnClickListener {
                onCardClick(book.id)
            }

            binding.llCounter.btnRight.setOnClickListener {
                onCountUpdate(book.id, book.inCartCount+1)
            }

            binding.llCounter.tvCounter.text = book.inCartCount.toString()


            binding.tvName.text = book.name
            binding.tvAuthor.text = book.author
            binding.tvPrice.text = binding.root.context.getString(R.string.price, book.price)
        }
    }
}