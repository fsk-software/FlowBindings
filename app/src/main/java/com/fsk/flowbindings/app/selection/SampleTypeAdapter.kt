package com.fsk.flowbindings.app.selection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsk.flowbindings.app.R

class SampleTypeAdapter : RecyclerView.Adapter<SampleTypeAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.titleView)

        init {
            itemView.setOnClickListener { onItemClick(adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Sample)
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false))
        { onItemClickListener?.onItemClick(Sample.values()[it]) }

    override fun getItemCount(): Int = Sample.values().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.setText(Sample.values()[position].titleStringResId)
    }
}