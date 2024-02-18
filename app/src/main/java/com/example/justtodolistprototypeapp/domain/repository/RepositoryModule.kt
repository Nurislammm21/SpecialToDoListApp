package com.example.justtodolistprototypeapp.domain.repository

import com.example.justtodolistprototypeapp.domain.models.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {


    @Provides
    fun provideTodoRepository(toDoDao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(toDoDao)
        // for unit testing
}