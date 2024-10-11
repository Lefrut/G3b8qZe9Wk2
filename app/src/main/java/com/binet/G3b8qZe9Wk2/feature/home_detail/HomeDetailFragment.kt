package com.binet.G3b8qZe9Wk2.feature.home_detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import com.binet.G3b8qZe9Wk2.R
import com.binet.G3b8qZe9Wk2.databinding.FragmentHomeDetailBinding
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import com.binet.G3b8qZe9Wk2.domain.repository.BinetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeDetailFragment : Fragment(R.layout.fragment_home_detail) {


    private val binetRepository by inject<BinetRepository>()
    private lateinit var binding: FragmentHomeDetailBinding
    private val supportActionBar get() = (requireActivity() as? AppCompatActivity)?.supportActionBar


    companion object {
        const val TAG = "home_detail"
        const val ID_ARG = "id"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentHomeDetailBinding.bind(view)

        val id = requireArguments().getInt(ID_ARG)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                createMedicationFlow(id).collectLatest { medication ->
                    binding.loadingBar.visibility = View.GONE
                    Log.i(TAG, medication.toString())
                    binding.desc.text = medication.description
                    binding.name.text = medication.name
                    binding.icon.load(medication.iconUrl) {
                        error(R.drawable.baseline_error_124)
                        crossfade(true)
                    }
                    binding.image.load(medication.imageUrl) {
                        error(R.drawable.baseline_error_124)
                        crossfade(true)
                    }
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onPrepareOptionsMenu(menu: Menu) {
        supportActionBar?.title = ""
        menu.clear()
        super.onPrepareOptionsMenu(menu)
    }


    private fun createMedicationFlow(id: Int): Flow<Medication> {
        binding.loadingBar.visibility = View.VISIBLE
        return flow {
            getDrug(id).onSuccess { medication ->
                emit(medication)
            }
        }.flowOn(Dispatchers.Main)
    }

    private suspend fun getDrug(id: Int): Result<Medication> {
        return binetRepository.getMedication(id)
    }

}