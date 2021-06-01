package com.soham.rcv_prac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soham.todo.R
import com.soham.todo.Todo
import java.util.*

class myAdapter(val sample_data_array: List<Todo>) : RecyclerView.Adapter<myAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        // this function is called initially when new view are created according to the number of them that can fit in the screen

        val item_layout = LayoutInflater.from(parent.context).inflate(R.layout.todo_item_view, parent, false)
        // this line bring the item layout in memory and inflates it
        // in any project this line is going to be the same only the name of the item layout xml would change

        return myViewHolder(item_layout)

    }

    override fun getItemCount() = sample_data_array.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(sample_data_array[position])
    }

    override fun getItemId(position: Int): Long {
        return sample_data_array[position].id    // made for making ID's
    }

    inner class myViewHolder(view: View) : RecyclerView.ViewHolder(view) { // its made inner class so that it can access the members of the out class i.e myAdapter class
        // "View" is the inflated View layout

        val titleView: TextView = view.findViewById(R.id.titleView)
        val subtitleView: TextView = view.findViewById(R.id.subtitleView)
        val Category: TextView = view.findViewById(R.id.categoyView)
        val dateView: TextView = view.findViewById(R.id.dateView)
        val timeView: TextView = view.findViewById(R.id.timeView)

        fun bind(data_item: Todo) {
            with(data_item)
            {
                titleView.text = title
                subtitleView.text = description
                Category.text = category
                dateView.text = "${date.get(Calendar.DAY_OF_MONTH).toString()}/${date.get(Calendar.MONTH)}/${date.get(Calendar.YEAR)}"
                timeView.text = "${date.get(Calendar.HOUR_OF_DAY).toString()}:${date.get(Calendar.MINUTE).toString()}"
            }
        }
    }


}