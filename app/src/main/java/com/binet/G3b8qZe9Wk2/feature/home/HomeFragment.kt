package com.binet.G3b8qZe9Wk2.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binet.G3b8qZe9Wk2.R
import com.binet.G3b8qZe9Wk2.feature.home_detail.HomeDetailFragment
import com.binet.G3b8qZe9Wk2.getAttrColor
import com.binet.G3b8qZe9Wk2.supportActionBar
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
        homeItemAdapterDelegate { homeItem ->
            val bundleOf = bundleOf(HomeDetailFragment.ID_ARG to homeItem.id)
            parentFragmentManager.commit {
                addToBackStack(null)
                replace<HomeDetailFragment>(R.id.main_container, HomeDetailFragment.TAG, bundleOf)
            }
        }
    )
    private val viewModel by viewModel<HomeViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(view)
        val progressCircular = view.findViewById<CircularProgressIndicator>(R.id.progress_circular)
        setHasOptionsMenu(true)

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

    override fun onStart() {
        supportActionBar?.title = getString(R.string.list)
        super.onStart()
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

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onPrepareOptionsMenu(menu)", "androidx.fragment.app.Fragment")
    )
    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        val searchTextView =
            searchView.findViewById<AutoCompleteTextView>(androidx.appcompat.R.id.search_src_text)
        val colorPrimary =
            requireContext().getAttrColor(com.google.android.material.R.attr.colorSurface)
        searchTextView.setTextColor(colorPrimary)

        searchView.setOnQueryTextListener(
            object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val strQuery = searchView.query.toString()
                    viewModel.loadMedications(strQuery)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrEmpty()){
                        viewModel.loadMedications(null)
                    }
                    return true
                }

            }
        )
        super.onPrepareOptionsMenu(menu)
    }
}


fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}