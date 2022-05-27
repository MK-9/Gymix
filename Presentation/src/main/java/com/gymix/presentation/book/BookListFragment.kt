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
    private var bookListAdapter1: BookListAdapter? = null
    private var bookListAdapter2: BookListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(inflater)

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.fetchBooks().collect { result ->
                    when (result) {
                        is RemoteStatus.Loading -> {
                            binding.prgMain.isVisible = result.state
                        }

                        is RemoteStatus.Success -> {
                            binding.rcv1.isVisible = true
                            binding.rcv2.isVisible = true
                            bookListAdapter1?.submitList(result.data?.bookList?.books)
                            bookListAdapter2?.submitList(result.data?.bookList?.books)
                        }

                        is RemoteStatus.Error -> {
                            binding.rcv1.isVisible = false
                            binding.rcv2.isVisible = false
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //init rcv1
        configRcv1()

        //init rcv2
        configRcv2()
    }

    private fun configRcv1() {
        with(binding.rcv1) {
            bookListAdapter1 = BookListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            addItemDecoration(HorizontalBookItemDecoration(context))
            adapter = bookListAdapter1
        }
    }

    private fun configRcv2() {
        with(binding.rcv2) {
            bookListAdapter2 = BookListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            addItemDecoration(HorizontalBookItemDecoration(context))
            adapter = bookListAdapter2
        }
    }
}