package com.sotti.watch.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sotti.ui.kit.dpToPx
import com.sotti.watch.about.view.databinding.AboutFragmentBinding
import com.sotti.watch.intents.loadChromeCustomTab

internal class AboutFragment : Fragment() {

    private lateinit var viewBinding: AboutFragmentBinding

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
        setUpElevations()
        setClickListeners()
        return viewBinding.root
    }

    private fun setUpElevations() {
        with(viewBinding) {
            val topOfTheCardContentElevation = card.elevation + dpToPx(1)
            profileImage.elevation = topOfTheCardContentElevation
            name.elevation = topOfTheCardContentElevation
            ocuppation.elevation = topOfTheCardContentElevation
            location.elevation = topOfTheCardContentElevation
        }
    }

    private fun setClickListeners() {
        with(viewBinding) {
            github.setOnClickListener { loadChromeCustomTab("https://github.com/Sottti") }
            medium.setOnClickListener { loadChromeCustomTab("https://medium.com/@sotti") }
            twitter.setOnClickListener { loadChromeCustomTab("https://twitter.com/Sotttti") }
            linkedin.setOnClickListener { loadChromeCustomTab("https://uk.linkedin.com/in/sotti") }
            stackoverflow.setOnClickListener { loadChromeCustomTab("https://stackoverflow.com/users/1177959/sotti") }
        }
    }
}
