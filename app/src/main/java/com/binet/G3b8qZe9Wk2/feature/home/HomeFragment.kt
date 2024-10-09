package com.binet.G3b8qZe9Wk2.feature.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binet.G3b8qZe9Wk2.R
import com.binet.G3b8qZe9Wk2.ui.adapter_delegates.generateHomeItems
import com.binet.G3b8qZe9Wk2.ui.adapter_delegates.homeItemAdapterDelegate
import com.binet.G3b8qZe9Wk2.ui.decorations.VerticalGridDecoration
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeAdapter = ListDelegationAdapter(
        homeItemAdapterDelegate { }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(view)
    }

    private fun setRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_recycler)
        recyclerView.adapter = homeAdapter.apply {
            items = generateHomeItems()
        }
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