package com.example.justtodolistprototypeapp.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.justtodolistprototypeapp.databinding.TodoListItemBinding
import com.example.justtodolistprototypeapp.domain.models.ToDoEntity

class ToDoAdapter(private val onItemClicked: (ToDoEntity) -> Unit): ListAdapter<ToDoEntity,ToDoAdapter.ToDoViewHolder>(
    DIFF_CALLBACK)   {

    class ToDoViewHolder(private val binding: TodoListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todo: ToDoEntity) = with(binding){
            titleItem.text = todo.title
            descItem.text = todo.description

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(TodoListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = currentList[position]
        holder.bind(todo)
        holder.itemView.setOnClickListener{
            onItemClicked(todo)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ToDoEntity>() {
            override fun areItemsTheSame(oldItem: ToDoEntity, newItem: ToDoEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ToDoEntity, newItem: ToDoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}