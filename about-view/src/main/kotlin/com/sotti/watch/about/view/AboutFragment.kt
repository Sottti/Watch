package com.sotti.watch.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sotti.ui.kit.dpToPx
import com.sotti.watch.about.view.AboutViewActions.OpenSocialMediaProfile
import com.sotti.watch.about.view.AboutViewIntents.*
import com.sotti.watch.about.view.databinding.AboutFragmentBinding
import com.sotti.watch.intents.loadChromeCustomTab
import com.sotti.watch.utils.ViewActionsHandler
import com.sotti.watch.utils.spin
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sotti.watch.about.view.AboutViewActions.*

internal class AboutFragment : Fragment(), ViewActionsHandler<AboutViewActions> {

    private val viewModel: AboutViewModel by viewModel()
    private lateinit var viewBinding: AboutFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectAboutModules()
        viewModel.actions.register(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.about_fragment,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpElevations()
        setClickListeners()
    }

    private fun setUpElevations() {
        with(viewBinding) {
            cardContent.elevation = card.elevation.plus(dpToPx(1))
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

    override fun handleAction(action: AboutViewActions) =
        when (action) {
            is OpenSocialMediaProfile -> loadChromeCustomTab(action.url)
            ShowEasterEgg -> viewBinding.profileImage.spin()
        }
}
