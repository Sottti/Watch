package com.sotti.watch.explore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sotti.watch.explore.view.databinding.ExploreFragmentBinding
import com.sotti.watch.explore.view.list.ExploreAdapter
import com.sotti.watch.utils.inflate
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ExploreFragment : Fragment(), Observer<ExploreViewStateUIM> {

    private var listAdapter = ExploreAdapter()
    private val viewModel: ExploreViewModel by viewModel()
    private lateinit var viewBinding: ExploreFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectExploreModules()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate(R.layout.explore_fragment, container)
        viewModel.viewState.observe(this, this@ExploreFragment)
        return viewBinding.root
    }

    override fun onChanged(viewState: ExploreViewStateUIM) {
        viewBinding.uiModel = ExploreViewStateUIMDecorator(viewState)
        listAdapter.submitList(viewState.movies)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }
}