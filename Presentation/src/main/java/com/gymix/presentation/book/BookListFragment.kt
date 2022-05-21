package com.gymix.presentation.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.presentation.databinding.FragmentBookListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListFragment : Fragment() {

    private val viewModel: BookListViewModel by viewModels()
    private lateinit var binding: FragmentBookListBinding
    private var bookListAdapter: BookListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(inflater)

        //init RecyclerView
        configRcv()

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.fetchBooks().collect { result ->
                    when (result) {
                        is RemoteStatus.Loading -> {
                            binding.prgMain.isVisible = result.state
                        }

                        is RemoteStatus.Success -> {
                            binding.rcvMainLayout.isVisible = true
                            bookListAdapter?.submitList(result.data?.bookList?.books)
                        }

                        is RemoteStatus.Error -> {
                            binding.rcvMainLayout.isVisible = false
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun configRcv() {
        with(binding.rcvMainLayout) {
            bookListAdapter = BookListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = bookListAdapter
        }
    }
}