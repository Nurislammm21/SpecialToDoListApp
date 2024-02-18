package com.example.justtodolistprototypeapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.justtodolistprototypeapp.R
import com.example.justtodolistprototypeapp.UI.adapters.ToDoAdapter
import com.example.justtodolistprototypeapp.databinding.FragmentToDoListMainBinding
import kotlinx.coroutines.launch


class ToDoListFragmentMain : Fragment() {
    private lateinit var binding: FragmentToDoListMainBinding
    private val viewModel : ToDoListVIewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToDoListMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun initRcView() = with(binding){
        val adapter = ToDoAdapter{
            val action = ToDoListFragmentMainDirections.actionToDoListFragmentMainToEditToDoListFragment()
            val bundle = bundleOf("id" to it.id)
            findNavController().navigate(action.actionId,bundle)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todoS.collect{
                adapter.submitList(it)
            }
        }

        rcView.adapter = adapter
        rcView.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        rcView.setHasFixedSize(true)
    }

}