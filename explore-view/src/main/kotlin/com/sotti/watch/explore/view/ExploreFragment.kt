package com.sotti.watch.explore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sotti.watch.explore.view.databinding.ExploreFragmentBinding

internal class ExploreFragment : Fragment() {

    private lateinit var viewBinding: ExploreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.explore_fragment, container, false
        )
        return viewBinding.root
    }
}