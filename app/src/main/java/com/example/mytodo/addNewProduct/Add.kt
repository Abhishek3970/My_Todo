package com.example.mytodo.addNewProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.mytodo.R
import com.example.mytodo.database.ItemDatabase
import com.example.mytodo.databinding.AddFragmentBinding

class Add : Fragment() {


    private lateinit var viewModel: AddViewModel
    private lateinit var binding: AddFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_fragment, container, false)

        viewModelSetup()

        toolbarSetup()

        checkToShowDelete()

        viewModel.done?.observe(viewLifecycleOwner , Observer {
            if(it==true){
                binding.toolbar.findNavController().navigateUp()
                viewModel.doneNavigation()
            }
        })





        return binding.root
    }

    private fun checkToShowDelete() {
        val arg = arguments?.let { AddArgs.fromBundle(it) }
        val flag = arg?.flag
        if (flag == 0) {
            binding.delete.visibility = View.INVISIBLE
        }
    }

    private fun viewModelSetup() {
        val application = requireNotNull(this.activity).application
        val dataSource = ItemDatabase.getInstance(application).itemDao
        val viewModelFactory = AddViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddViewModel::class.java)
        binding.lifecycleOwner = this
        binding.addViewModel = viewModel
    }

    private fun toolbarSetup() {
        binding.toolbar.inflateMenu(R.menu.menu_save)
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.save) {
                if (binding.heading.text.toString().trim().isNotEmpty() && binding.description.text.toString().trim().isNotEmpty()) {
                    viewModel.save(
                        binding.heading.text.toString().trim(),
                        binding.description.text.toString().trim()
                    )
                } else {
                    Toast.makeText(context!!, "Enter all fields", Toast.LENGTH_LONG).show()
                }
            }
            true
        }
        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }


}
