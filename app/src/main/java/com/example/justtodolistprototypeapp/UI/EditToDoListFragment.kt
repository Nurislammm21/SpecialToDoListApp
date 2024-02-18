package com.example.justtodolistprototypeapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.justtodolistprototypeapp.R
import com.example.justtodolistprototypeapp.databinding.FragmentEditToDoListBinding
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EditToDoListFragment : Fragment() {
    private lateinit var binding: FragmentEditToDoListBinding
    private val viewModel: ToDoListVIewModel by viewModels()
    lateinit var todo: ToDoEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLogic()
        val id = requireArguments().getInt("id")
        viewModel.retrieveToDo(id)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todo.collect { selectedTodo ->
                selectedTodo?.let {
                    todo = selectedTodo
                    bind(todo)
                }
            }
        }
    }

    private fun initLogic() = with(binding) {
        toolbarEditTodo.deleteClick = {
            if (todoIsInitialized()) {
                viewModel.deleteToDo(todo)
            }
        }
        toolbarEditTodo.saveClick = { saveEntry() }
    }

    private fun bind(todo: ToDoEntity) {
        binding.apply {
            todoTitle.setText(todo.title)
            todoDescription.setText(todo.description)
        }
    }

        private fun saveEntry() = with(binding) {
            val todoTitle = todoTitle.text.toString()
            val todoDescription = todoDescription.text.toString()

            if (viewModel.isEntryValid(todoTitle, todoDescription)) {
                if (todoIsInitialized()) {
                    viewModel.updateToDO(todo.id, todoTitle, todoDescription)
                } else {
                    viewModel.addNewToDo(todoTitle, todoDescription)
                }
            }
        }

    private fun todoIsInitialized() = ::todo.isInitialized
}


