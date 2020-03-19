package com.example.mytodo.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.mytodo.R
import com.example.mytodo.database.ItemDatabase
import com.example.mytodo.databinding.HomepageFragmentBinding

class Homepage : Fragment() {


    private lateinit var viewModel: HomepageViewModel
    private lateinit var binding: HomepageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.homepage_fragment, container, false)

        binding.toolbar.inflateMenu(R.menu.menu_delete_all)
        binding.lifecycleOwner = this


        viewModelSetup()


        val adapter = Adapter()
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner , Observer {
            it?.let{
                adapter.data = it
            }
        })

        viewModel.toAddProduct?.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.toolbar.findNavController()
                    .navigate(HomepageDirections.actionHomepageToAdd2(flag = 0))
                viewModel.doneToAdd()
            }
        })

        return binding.root
    }

    private fun viewModelSetup() {
        val application = requireNotNull(this.activity).application
        val dataSource = ItemDatabase.getInstance(application).itemDao
        val viewModelFactory = HomePageVewModelFactory(dataSource, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomepageViewModel::class.java)
        binding.viewModel = viewModel
    }


}
