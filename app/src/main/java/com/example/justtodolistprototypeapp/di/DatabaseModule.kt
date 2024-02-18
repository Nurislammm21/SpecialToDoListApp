package com.example.justtodolistprototypeapp.di

import android.content.Context
import androidx.room.Room
import com.example.justtodolistprototypeapp.domain.models.ToDoDao
import com.example.justtodolistprototypeapp.domain.models.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {
    @Provides
    @ViewModelScoped
    fun provideLocalDataBase(
        @ApplicationContext context: Context) : ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java,"todo_database").build()


    @Provides
    @ViewModelScoped
    fun provideToDoDao(todoDatabase : ToDoDatabase): ToDoDao = todoDatabase.todoDao()


}