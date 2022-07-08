package com.gymix.store

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gymix.common.utils.GridManager
import com.gymix.common.utils.network.RemoteStatus
import com.gymix.store.databinding.FragmentBookListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : Fragment() {

    private val viewModel: BookListViewModel by viewModels()
    private lateinit var binding: FragmentBookListBinding
    private var bookListAdapter1: BookListAdapter? = null
    private var gridBookListAdapter: GridBookListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(inflater)

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
//                viewModel.fetchBooks().collect { result ->
//                    when (result) {
//                        is RemoteStatus.Loading -> {
//                            binding.prgMain.isVisible = result.state
//                        }
//
//                        is RemoteStatus.Success -> {
////                            binding.rcv1.isVisible = true
//                            binding.rcv2.isVisible = true
//                            gridBookListAdapter?.submitList(result.data?.bookList?.books)
////                            bookListAdapter1?.submitList(result.data?.bookList?.books)
////                            bookListAdapter2?.submitList(result.data?.bookList?.books)
//                        }
//
//                        is RemoteStatus.Error -> {
//                            binding.rcv1.isVisible = false
////                            binding.rcv2.isVisible = false
//                        }
//                    }
//                }

                viewModel.getApiToken().collect { result ->
                    when (result) {
                        is RemoteStatus.Loading -> {
                            binding.prgMain.isVisible = result.state
                        }

                        is RemoteStatus.Success -> {
                            binding.rcv2.isVisible = true
                        }

                        is RemoteStatus.Error -> {
                            binding.rcv1.isVisible = false
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //init rcv1
//        configLinearRcv()

        //init rcv2
        configGridRcv()
    }

    private fun configLinearRcv() {
        with(binding.rcv1) {
            bookListAdapter1 = BookListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            addItemDecoration(HorizontalBookItemDecoration(context))
            adapter = bookListAdapter1
        }
    }

    private fun configGridRcv() {
        with(binding.rcv2) {
            val gridManager = GridManager(context)
            gridBookListAdapter = GridBookListAdapter(gridManager)
            layoutManager = GridLayoutManager(
                context,
                gridManager.getSpanCountForGridRcv(context),
                RecyclerView.VERTICAL,
                false
            )
            addItemDecoration(GridBookItemDecoration(context, gridManager))
            adapter = gridBookListAdapter
        }
    }
}