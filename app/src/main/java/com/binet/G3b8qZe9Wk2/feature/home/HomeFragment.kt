package com.binet.G3b8qZe9Wk2.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binet.G3b8qZe9Wk2.R
import com.binet.G3b8qZe9Wk2.ui.adapter_delegates.homeItemAdapterDelegate
import com.binet.G3b8qZe9Wk2.ui.adapter_delegates.toHomeItem
import com.binet.G3b8qZe9Wk2.ui.decorations.VerticalGridDecoration
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeAdapter = ListDelegationAdapter(
        homeItemAdapterDelegate { }
    )
    private val viewModel by viewModel<HomeViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(view)
        val progressCircular = view.findViewById<CircularProgressIndicator>(R.id.progress_circular)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collectLatest { value ->
                    if (value) {
                        progressCircular.visibility = View.VISIBLE
                    } else {
                        progressCircular.visibility = View.GONE
                        viewModel.medicationsState.collectLatest { medicationList ->
                            homeAdapter.items =
                                medicationList.map { medication -> medication.toHomeItem() }
                            homeAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

    }

    private fun setRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_recycler)
        recyclerView.adapter = homeAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            VerticalGridDecoration(
                2,
                16.dpToPx(requireContext()),
                true
            )
        )
    }
}

fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}