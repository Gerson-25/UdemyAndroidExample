package com.example.udemyandroidexample.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.udemyandroidexample.R
import com.example.udemyandroidexample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val listAdapter = DogListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_dog_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogs.observe( this, Observer {dogs ->
            dogs?.let {
                rv_dog_list.visibility = View.VISIBLE
                listAdapter.updateDogList(it)
            }

        })

        viewModel.dogsLoadError.observe(this, Observer {error ->
            error?.let {
                text_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer {loading ->
            loading?.let {
                progress_data_loading.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    text_error.visibility = View.GONE
                    progress_data_loading.visibility = View.GONE
                }
            }
        })
    }


}
