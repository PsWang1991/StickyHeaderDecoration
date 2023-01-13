package com.example.stickyheadertest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stickyheadertest.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.adapter = NumberAdapter(viewModel.mockData)
        binding.recyclerView.addItemDecoration(StickyHeaderItemDecoration(
            viewModel.mockData,
            requireContext(),
            0,
            0.dp.toInt(),
            0,
            0.dp.toInt()
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}