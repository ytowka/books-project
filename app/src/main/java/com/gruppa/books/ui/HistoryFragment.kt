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
import com.gruppa.books.R
import com.gruppa.books.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding

    val viewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = HistoryAdapter(
            onCardClick = {
                findNavController().navigate(
                    R.id.action_history_to_orderFragment,
                    OrderFragment.createBundle(it)
                )
                Log.d("debugg", "onCardClick() called $it")
            },
        )
        val gridLayout = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = gridLayout
        }
        viewModel.history.observe(viewLifecycleOwner) {
            historyAdapter.list = it
        }
    }
}