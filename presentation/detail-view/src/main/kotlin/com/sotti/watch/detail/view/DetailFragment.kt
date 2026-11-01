package com.sotti.watch.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sotti.watch.detail.view.databinding.DetailFragmentBinding
import com.sotti.watch.utils.inflate

internal class DetailFragment : Fragment() {

    private lateinit var viewBinding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate(R.layout.detail_fragment, container)
        return viewBinding.root
    }

}