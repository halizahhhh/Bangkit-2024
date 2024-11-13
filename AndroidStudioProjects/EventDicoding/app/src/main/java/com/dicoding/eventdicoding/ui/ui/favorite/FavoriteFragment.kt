package com.dicoding.eventdicoding.ui.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.eventdicoding.data.Injection.messageError
import com.dicoding.eventdicoding.data.local.entity.FavoriteEvent
import com.dicoding.eventdicoding.databinding.FragmentFavoriteBinding
import com.dicoding.eventdicoding.ui.FavoriteAdapter
import com.dicoding.eventdicoding.ui.ViewModelFactory

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        val adapter = FavoriteAdapter()

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            favoriteViewModel.isLoading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            fun updateEventList(events: List<FavoriteEvent>) {
                adapter.submitList(events)
            }

            favoriteViewModel.getAllFavorite().observe(viewLifecycleOwner) { data ->
                data?.let {
                    updateEventList(it)
                } ?: messageError(requireActivity(), "Failed to initialize data")
            }

        }
    return binding.root
    }
}
