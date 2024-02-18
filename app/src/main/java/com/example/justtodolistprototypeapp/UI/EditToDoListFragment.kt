package com.example.justtodolistprototypeapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.justtodolistprototypeapp.R
import com.example.justtodolistprototypeapp.databinding.FragmentEditToDoListBinding


class EditToDoListFragment : Fragment() {
    private lateinit var binding : FragmentEditToDoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentEditToDoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    }
