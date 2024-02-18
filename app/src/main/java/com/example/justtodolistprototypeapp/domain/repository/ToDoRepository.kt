package com.example.justtodolistprototypeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.justtodolistprototypeapp.domain.models.ToDoDao
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ToDoRepository {
        suspend fun insert(todo : ToDoEntity)
        suspend fun update(todo : ToDoEntity)
        suspend fun delete(todo : ToDoEntity)

        suspend fun getToDo(id : Int): Flow<ToDoEntity>

        fun getToDOs() : Flow<List<ToDoEntity>>

        fun getAllToDoAsc() : LiveData<List<ToDoEntity>>

        suspend fun deleteAll()


}


class ToDoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository{
        override suspend fun insert(todo: ToDoEntity) = withContext(Dispatchers.IO) {
                        toDoDao.insert(todo)
        }

        override suspend fun update(todo: ToDoEntity) = withContext(Dispatchers.IO) {
                toDoDao.update(todo)
        }

        override suspend fun delete(todo: ToDoEntity) = withContext(Dispatchers.IO) {
              toDoDao.delete(todo)
        }

        override suspend fun getToDo(id: Int): Flow<ToDoEntity> = withContext(Dispatchers.IO) {
                toDoDao.getToDO(id)
        }

        override fun getToDOs(): Flow<List<ToDoEntity>> = toDoDao.getToDOs()

        override fun getAllToDoAsc(): LiveData<List<ToDoEntity>> {
               return toDoDao.getAllToDoAsc()
        }

        override suspend fun deleteAll() {
                toDoDao.deleteAll()
        }

}