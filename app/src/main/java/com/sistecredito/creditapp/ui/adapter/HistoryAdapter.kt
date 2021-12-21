package com.sistecredito.creditapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.data.model.Users

class HistoryAdapter(val listCC: MutableList<Users>): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class HistoryViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val userCC = view.findViewById<TextView>(R.id.user)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_history_card, parent, false)
        return HistoryViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val list = listCC[position]
        holder.userCC.text = list.cc.toString()
    }

    override fun getItemCount(): Int = listCC.size
}