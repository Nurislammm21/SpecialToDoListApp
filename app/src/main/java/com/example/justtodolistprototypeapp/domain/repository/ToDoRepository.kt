package com.example.justtodolistprototypeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
        suspend fun insert(todo : ToDoEntity)
        suspend fun update(todo : ToDoEntity)
        suspend fun delete(todo : ToDoEntity)

        suspend fun getToDo(id : Int): Flow<ToDoEntity>

        fun getToDOs() : Flow<List<ToDoEntity>>

        fun getAllToDoAsc() : LiveData<List<ToDoEntity>>

        suspend fun deleteAll()


}