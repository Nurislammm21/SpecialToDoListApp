package com.example.justtodolistprototypeapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justtodolistprototypeapp.domain.data.ToDoItem
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity
import com.example.justtodolistprototypeapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
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

    private fun insertToDo(todo:ToDoEntity){
    viewModelScope.launch {
        toDoRepository.insert(todo)
    } }

    private fun getNewToDoEntry(toDoTitle: String, todoDescription: String): ToDoEntity {
        return ToDoEntity(
            title = toDoTitle,
            description = todoDescription
        )
    }

    private fun addNewToDo(toDoTitle: String, todoDescription: String){
        val newToDo = getNewToDoEntry(toDoTitle,todoDescription)
        insertToDo(newToDo)
    }


    private fun updateToDo(todo: ToDoEntity){
        viewModelScope.launch {
            toDoRepository.update(todo)
        } }

    private fun getUpdateToDoEntry(todoId: Int, toDoTitle: String, todoDescription: String): ToDoEntity{
        return ToDoEntity(id = todoId, title = toDoTitle, description = todoDescription)
    }
     fun updateToDO(todoId: Int, toDoTitle: String, todoDescription: String){
        val updatedTodo = getUpdateToDoEntry(todoId,toDoTitle,todoDescription)
        updateToDo(updatedTodo)
    }

    fun deleteToDo(todo: ToDoEntity){
        viewModelScope.launch {
            toDoRepository.delete(todo)
        } }

    fun retrieveToDo(id: Int){
        viewModelScope.launch {
            toDoRepository.getToDo(id).collect{
                todo.value = it
            }
        }
    }

    fun clear(){
        viewModelScope.launch {
            toDoRepository.deleteAll()
        }
    }





}