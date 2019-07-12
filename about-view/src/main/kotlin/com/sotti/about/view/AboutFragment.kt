package com.sotti.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sotti.about.view.databinding.AboutFragmentBinding
import com.sotti.intents.loadChromeCustomTab

internal class AboutFragment : Fragment() {

    private lateinit var viewBinding: AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.about_fragment, container, false
        )
        viewBinding.clickHandler = AboutClickHandler()
        return viewBinding.root
    }

    @Suppress("UNUSED_PARAMETER")
    inner class AboutClickHandler {

        fun onGithubClick(view: View) {
            loadChromeCustomTab("https://github.com/Sottti")
        }

        fun onStackOverflowClick(view: View) {
            loadChromeCustomTab("https://stackoverflow.com/users/1177959/sotti")
        }

        fun onMediumClick(view: View) {
            loadChromeCustomTab("https://medium.com/@sotti")
        }

        fun onTwitterClick(view: View) {
            loadChromeCustomTab("https://twitter.com/Sotttti")
        }

        fun onLinkedInClick(view: View) {
            loadChromeCustomTab("https://uk.linkedin.com/in/sotti")
        }
    }
}
