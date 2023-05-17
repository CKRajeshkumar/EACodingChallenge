package com.app.eacodingchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.eacodingchallenge.R
import com.app.eacodingchallenge.models.Root

class RecyclerViewAdapter(
    private val context: Context,
    private var list: List<Root>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.mainTitle)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            message.text = recyclerViewModel.bands.first().recordLabel
        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.mainTitle)
        var message2: TextView = itemView.findViewById(R.id.subTitle)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            message.text =
                recyclerViewModel.bands.first().name
            message2.text = recyclerViewModel.name
            if (recyclerViewModel.name.isNullOrEmpty()) {
                message2.visibility = View.GONE
            } else {
                message2.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_view_1, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view_2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    fun updateList(newList: List<Root>) {
        list = newList
        notifyDataSetChanged()
    }
}