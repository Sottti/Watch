package com.sotti.watch.explore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sotti.watch.explore.view.databinding.ExploreFragmentBinding
import com.sotti.watch.explore.view.list.ExploreAdapter
import com.sotti.watch.explore.view.list.ExploreMovieVH.OnExploreItemIntentsListener
import com.sotti.watch.utils.inflate
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ExploreFragment : Fragment(), OnExploreItemIntentsListener, Observer<Any> {

    companion object {
        const val ARG_AVOID_INJECTIONS = "skipInjections"
    }

    private var listAdapter = ExploreAdapter(this)
    private val viewModel: ExploreViewModel by viewModel()
    private lateinit var viewBinding: ExploreFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val skipInjections = arguments?.getBoolean(ARG_AVOID_INJECTIONS, true) == true
        if (!skipInjections) injectExploreModules()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate(R.layout.explore_fragment, container)
        viewModel.viewState.observe(this, this@ExploreFragment)
        viewModel.viewEvent.observe(this, this@ExploreFragment)
        viewBinding.errorView.onRetryClickListener = { viewModel.onRetry() }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.content.apply {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    override fun onChanged(t: Any?) {
        when (t) {
            is ExploreViewStateUIM -> onViewStateUpdate(t)
            is ExploreViewEventUIM -> onViewEventReceived(t)
        }
    }

    private fun onViewStateUpdate(viewState: ExploreViewStateUIM) {
        viewBinding.uiModel = ExploreViewStateUIMDecorator(viewState)
        listAdapter.submitList(viewState.movies)
    }

    private fun onViewEventReceived(viewEvent: ExploreViewEventUIM) {
        /*when (viewEvent) {
            is ShowMovieDetails ->
                findNavController().navigate(R.id.action_explore_to_detail_fragment)
        }*/
    }

    override fun showDetails(movieId: Int) {
        viewModel.onMovieShowDetails(movieId)
    }
}