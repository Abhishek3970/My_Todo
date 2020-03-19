package com.example.mytodo.homePage

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        viewModelSetup()
        binding.toolbar.inflateMenu(R.menu.menu_delete_all)
        binding.toolbar.setOnMenuItemClickListener {
            alertDialogToConfirm()
            true
        }
        binding.lifecycleOwner = this





        val adapter = Adapter(
            ItemClickListener { item ->
                binding.toolbar.findNavController().navigate(
                    HomepageDirections.actionHomepageToAdd2(
                        flag = 1,
                        id = item.id,
                        heading = item.heading,
                        description = item.description,
                        time = item.time
                    )
                )
            }
        )
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.toAddProduct?.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.doneToAdd()
                binding.toolbar.findNavController()
                    .navigate(
                        HomepageDirections.actionHomepageToAdd2(
                            flag = 0,
                            id = 0L,
                            heading = "",
                            description = "",
                            time = ""
                        )
                    )
            }
        })

        return binding.root
    }

    private fun alertDialogToConfirm() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Are you sure")
        builder.setMessage("This will delete all the TO-DOs!!")
        builder.setNegativeButton("Delete All") { dialogInterface, i ->
            viewModel.clear()
            Toast.makeText(context!!, "Deleted", Toast.LENGTH_LONG).show()
        }
        val alertDialog = builder.create()
        alertDialog.show()
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
