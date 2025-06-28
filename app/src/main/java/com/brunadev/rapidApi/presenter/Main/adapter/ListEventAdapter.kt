package com.brunadev.rapidApi.presenter.Main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunadev.rapidApi.R
import com.brunadev.rapidApi.model.ResultApi
import kotlinx.android.synthetic.main.indexes_item.view.end_index
import kotlinx.android.synthetic.main.indexes_item.view.name_index
import kotlinx.android.synthetic.main.indexes_item.view.short_index
import kotlinx.android.synthetic.main.indexes_item.view.start_index

class ListEventAdapter(private val listEvents: List<ResultApi>, val onClick: (ResultApi) -> Unit) :
    RecyclerView.Adapter<ListEventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.indexes_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listEvents.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(listEvents[position])
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: ResultApi) {
            with(itemView) {
                setOnClickListener {
                    onClick.invoke(list)
                }

                name_index.text = list.shortName
                short_index.text = list.fullExchangeName
                start_index.text = (list.spark?.close?.get(0) ?: "0.0").toString()
                end_index.text = (list.spark?.previousClose ?: "0.0").toString()
            }
        }
    }
}
