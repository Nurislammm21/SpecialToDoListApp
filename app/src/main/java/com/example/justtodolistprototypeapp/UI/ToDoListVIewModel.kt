package com.example.justtodolistprototypeapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.justtodolistprototypeapp.domain.data.ToDoItem
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity
import com.example.justtodolistprototypeapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ToDoListVIewModel @Inject constructor(private val toDoRepository: ToDoRepository): ViewModel() {
    val todoS : Flow<List<ToDoEntity>> = toDoRepository.getToDOs()
    val todo = MutableStateFlow<ToDoEntity?>(null)

    private val allToDo: LiveData<List<ToDoEntity>> = toDoRepository.getAllToDoAsc()

    private val _todos = MediatorLiveData<List<ToDoItem>>().apply {
        addSource(allToDo){ todoNur ->
            val todoItems = todoNur.map {todo ->
                ToDoItem(todo.id,todo.title,todo.description)

            }
            postValue(todoItems)


        }
    }

    val todos: LiveData<List<ToDoItem>> = _todos

}