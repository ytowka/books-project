package com.gruppa.books.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruppa.books.R
import com.gruppa.books.databinding.ItemCatalogBookBinding
import com.gruppa.books.models.Book

class CatalogAdapter(
    val onCardClick: (Long) -> Unit,
    val onBookBuy: (Long) -> Unit,
    val onBookDelete: (Long) -> Unit,
    val onBookAdd: (Long) -> Unit,
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    var list: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return CatalogViewHolder(
            ItemCatalogBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CatalogViewHolder(val binding: ItemCatalogBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {

            Glide
                .with(binding.root.context)
                .load(book.imageUrl)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onCardClick(book.id)
            }

            binding.btnBuy.setOnClickListener {
                onBookBuy(book.id)
            }

            binding.tvName.text = book.name
            binding.tvPrice.text = binding.root.context.getString(R.string.price, book.price)
            binding.tvAuthor.text = book.author

            if (book.inCartCount != 0) {
                binding.btnBuy.visibility = View.GONE
                binding.btnCounter.root.visibility = View.VISIBLE

                binding.btnCounter.tvCounter.text = book.inCartCount.toString()

                binding.btnCounter.btnLeft.setOnClickListener {
                    onBookDelete(book.id)
                }

                binding.btnCounter.btnRight.setOnClickListener {
                    onBookAdd(book.id)
                }
            } else {
                binding.btnBuy.visibility = View.VISIBLE
                binding.btnCounter.root.visibility = View.GONE

            }
        }
    }
}