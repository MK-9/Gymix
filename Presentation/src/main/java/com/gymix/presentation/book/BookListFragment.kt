package com.gymix.presentation.book

import android.os.Bundle
import android.util.Log
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
import com.gymix.common.Result
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

                binding.apply {
                    viewModel.stateFlow.collect { result ->
                        when (result) {
                            is Result.InProgress -> {
                                prgMain.isVisible = result.state
                            }

                            is Result.OnSuccess -> {
                                rcvMainLayout.isVisible = true
                                Log.d("soltan", result.response.toString())
                                bookListAdapter?.submitList(result.response)
                            }

                            is Result.OnError -> {
                                rcvMainLayout.isVisible = false
                            }
                        }
                    }
                }
            }
        }

        //fetch data
        viewModel.fetchBook()

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