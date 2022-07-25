package com.greemoid.githubmessanger.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.databinding.FragmentSearchBinding
import com.greemoid.githubmessanger.presentation.main.BaseFragment
import com.greemoid.githubmessanger.sl.core.Feature


class SearchFragment : BaseFragment<SearchViewModel>() {

    override fun viewModelClass() = SearchViewModel::class.java
    override val titleResId = R.string.title_search
    override fun name() = Feature.SEARCH.name

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(SimpleQueryListener(viewModel))
        val adapter = SearchUserAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.observe(this) { it.map(adapter) }
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchView.setOnQueryTextListener(null)
        _binding = null
    }
}