package com.example.mytodo.addNewProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.mytodo.R
import com.example.mytodo.databinding.AddFragmentBinding

class Add : Fragment() {


    private lateinit var viewModel: AddViewModel
    private lateinit var binding: AddFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.add_fragment , container , false)
        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)



        return binding.root
    }


}
