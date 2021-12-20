package com.sistecredito.creditapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.data.model.Users

class HistotryAdapter(val listCC: MutableList<Users>): RecyclerView.Adapter<HistotryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userCC = view.findViewById<TextView>(R.id.user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_history_card, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val list = listCC[position].cc
        holder.userCC.text = list.toString()
    }

    override fun getItemCount(): Int = listCC.size
}