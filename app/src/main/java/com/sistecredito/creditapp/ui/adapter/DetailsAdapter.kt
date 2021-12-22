package com.sistecredito.creditapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.data.model.Users
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DetailsAdapter(val listCC: MutableList<Float>): RecyclerView.Adapter<DetailsAdapter.FeeViewHolder>() {
    class FeeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val number = view.findViewById<TextView>(R.id.number)
        val value = view.findViewById<TextView>(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_fee, parent, false)
        return FeeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FeeViewHolder, position: Int) {
        val list = listCC[position]
        val dec = DecimalFormat("#,###")
        if (position != listCC.size) {
            holder.number.text = (position+1).toString()
            holder.value.text = "$${dec.format(list)}"
        }
    }

    override fun getItemCount(): Int = listCC.size
}