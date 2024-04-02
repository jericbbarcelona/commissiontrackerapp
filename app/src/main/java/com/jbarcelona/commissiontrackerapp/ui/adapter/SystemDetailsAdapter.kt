package com.jbarcelona.commissiontrackerapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jbarcelona.commissiontrackerapp.R
import com.jbarcelona.commissiontrackerapp.network.response.SystemDetailsAndNote

class SystemDetailsAdapter(
    var systemDetailsAndNoteList: List<SystemDetailsAndNote>
) : RecyclerView.Adapter<SystemDetailsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_system_details_and_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = systemDetailsAndNoteList[position]
        holder.tvItemType.text = "${item.itemType}: "
        holder.tvQuantity.text = item.quantity.toString()
    }

    override fun getItemCount() = systemDetailsAndNoteList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemType: TextView = view.findViewById(R.id.tv_item_type)
        val tvQuantity: TextView = view.findViewById(R.id.tv_quantity)
    }
}