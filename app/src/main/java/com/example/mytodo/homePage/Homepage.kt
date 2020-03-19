package com.example.mytodo.homePage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.mytodo.R
import com.example.mytodo.databinding.HomepageFragmentBinding

class Homepage : Fragment() {



    private lateinit var viewModel: HomepageViewModel
    private lateinit var binding: HomepageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.homepage_fragment , container , false)
        viewModel = ViewModelProviders.of(this).get(HomepageViewModel::class.java)
        binding.toolbar.inflateMenu(R.menu.menu_delete_all)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        return binding.root
    }


}
