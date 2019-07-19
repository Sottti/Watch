package com.sotti.watch.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sotti.ui.kit.dpToPixel
import com.sotti.watch.about.view.AboutViewActions.OpenSocialMediaProfile
import com.sotti.watch.about.view.AboutViewActions.ShowEasterEgg
import com.sotti.watch.about.view.AboutViewIntents.*
import com.sotti.watch.about.view.databinding.AboutFragmentBinding
import com.sotti.watch.intents.loadChromeCustomTab
import com.sotti.watch.utils.exhaustive
import com.sotti.watch.utils.inflate
import com.sotti.watch.utils.spin
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AboutFragment : Fragment() {

    private val viewModel: AboutViewModel by viewModel()
    private lateinit var viewBinding: AboutFragmentBinding

    companion object {
        const val ARG_AVOID_INJECTIONS = "skipInjections"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val avoidInjections = arguments?.getBoolean(ARG_AVOID_INJECTIONS, true) == true
        if (!avoidInjections) injectAboutModules()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate(R.layout.about_fragment, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpElevations()
        setClickListeners()
        viewModel.actions.observe(this, Observer { action ->
            when (action) {
                is OpenSocialMediaProfile -> loadChromeCustomTab(action.url)
                ShowEasterEgg -> viewBinding.profileImage.spin()
            }.exhaustive
        })
    }

    private fun setUpElevations() {
        with(viewBinding) {
            cardContent.elevation = card.elevation.plus(1f.dpToPixel(requireContext()))
        }
    }

    private fun setClickListeners() {
        with(viewBinding) {
            github.setOnClickListener { viewModel.onIntent(OnOpenGithubProfile) }
            medium.setOnClickListener { viewModel.onIntent(OnOpenMediumProfile) }
            twitter.setOnClickListener { viewModel.onIntent(OnOpenTwitterProfile) }
            linkedin.setOnClickListener { viewModel.onIntent(OnOpenLinkedInProfile) }
            stackoverflow.setOnClickListener { viewModel.onIntent(OnOpenStackOverflowProfile) }
            profileImage.setOnClickListener { viewModel.onIntent(OnShowEasterEgg) }
        }
    }
}
