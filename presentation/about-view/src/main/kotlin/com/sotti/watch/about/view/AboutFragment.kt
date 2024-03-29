package com.sotti.watch.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sotti.watch.about.view.AboutViewActions.OpenSocialMediaProfile
import com.sotti.watch.about.view.AboutViewActions.ShowEasterEgg
import com.sotti.watch.about.view.databinding.AboutFragmentBinding
import com.sotti.watch.intents.loadChromeCustomTab
import com.sotti.watch.utils.dpToPixel
import com.sotti.watch.utils.exhaustive
import com.sotti.watch.utils.inflate
import com.sotti.watch.utils.spin
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AboutFragment : Fragment() {

    companion object {
        const val ARG_AVOID_INJECTIONS = "skipInjections"
    }

    private val viewModel: AboutViewModel by viewModel()

    private lateinit var viewBinding: AboutFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val skipInjections = arguments?.getBoolean(ARG_AVOID_INJECTIONS, true) == true
        if (!skipInjections) injectAboutModules()
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
            github.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnOpenGithubProfile) }
            medium.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnOpenMediumProfile) }
            twitter.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnOpenTwitterProfile) }
            linkedin.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnOpenLinkedInProfile) }
            stackoverflow.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnOpenStackOverflowProfile) }
            profileImage.setOnClickListener { viewModel.onIntent(AboutViewIntents.OnShowEasterEgg) }
        }
    }
}
