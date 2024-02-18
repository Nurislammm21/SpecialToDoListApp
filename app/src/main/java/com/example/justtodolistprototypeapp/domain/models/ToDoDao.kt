package com.example.justtodolistprototypeapp.domain.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoEntity)

    @Update
    suspend fun update(todo: ToDoEntity)

    @Delete
    suspend fun delete(todo: ToDoEntity)


    @Query("SELECT * FROM todo WHERE id = :id")
    fun getToDO(id: Int) : Flow<ToDoEntity>

    @Query("SELECT * FROM todo ORDER BY title ASC ")
    fun getToDOs() : Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getAllToDoAsc() : LiveData<List<ToDoEntity>>

    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}