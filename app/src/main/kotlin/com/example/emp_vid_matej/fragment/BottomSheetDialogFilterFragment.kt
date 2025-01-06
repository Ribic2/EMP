package com.example.emp_vid_matej.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.emp_vid_matej.databinding.FragmentBottomSheetDialogBinding
import com.example.emp_vid_matej.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class BottomSheetDialogFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetDialogBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            movieViewModel.movieResults.collectLatest { moviesResponse ->

                val availableGenres = moviesResponse.filters?.available?.genres ?: emptyList()
                updateGenresChipGroup(availableGenres)

                val appliedGenres = moviesResponse.filters?.applied?.genres ?: emptyList()
                preCheckChips(appliedGenres)
            }
        }


        binding.btnApplyFilters.setOnClickListener {
            val selectedGenres = getSelectedGenres()
            Log.d("filter", selectedGenres.toString())

            movieViewModel.currentFilter.value.genres = selectedGenres
            movieViewModel.getMovies(movieViewModel.currentFilter.value)

            dismiss()
        }
    }

    private fun updateGenresChipGroup(genres: List<String>) {
        binding.genreChipGroup.removeAllViews()
        for (genre in genres) {
            val chip = Chip(requireContext()).apply {
                text = genre
                isCheckable = true
                isClickable = true
            }
            binding.genreChipGroup.addView(chip)
        }
    }

    private fun preCheckChips(appliedGenres: List<String>) {
        // If you want to automatically check any previously applied genres
        for (i in 0 until binding.genreChipGroup.childCount) {
            val chip = binding.genreChipGroup.getChildAt(i) as? com.google.android.material.chip.Chip
            chip?.isChecked = appliedGenres.contains(chip?.text?.toString())
        }
    }

    private fun getSelectedGenres(): List<String> {
        val selectedGenres = mutableListOf<String>()
        for (i in 0 until binding.genreChipGroup.childCount) {
            val chip = binding.genreChipGroup.getChildAt(i) as? com.google.android.material.chip.Chip
            if (chip?.isChecked == true) {
                selectedGenres.add(chip.text.toString())
            }
        }
        return selectedGenres
    }

    companion object {
        const val TAG = "MyBottomSheetDialog"
    }
}