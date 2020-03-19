package com.example.mytodo.homePage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mytodo.R

class Homepage : Fragment() {

    companion object {
        fun newInstance() = Homepage()
    }

    private lateinit var viewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.homepage_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomepageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
