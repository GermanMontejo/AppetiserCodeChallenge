package com.germanmontejo.appetisercodechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.germanmontejo.appetisercodechallenge.R
import com.germanmontejo.appetisercodechallenge.model.Result

class ItunesTrackAdapter(itunesResults: List<Result>, isTwoPane: Boolean) : RecyclerView.Adapter<ItunesTrackHolder>() {
    private var itunes = itunesResults
    private val twoPane = isTwoPane
    private var isSortedAscending = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesTrackHolder {
        return ItunesTrackHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_content, parent, false), twoPane
        )
    }

    override fun getItemCount() = itunes.size

    override fun onBindViewHolder(holder: ItunesTrackHolder, position: Int) {
        holder.bindViewHolder(itunes[position])
    }

    fun sortResult(): String {
        var sortedBy: String

        if (!isSortedAscending) {
            sortedBy = "ascending"
            itunes = itunes.sortedBy { it.trackName }
        } else {
            sortedBy = "descending"
            itunes = itunes.sortedByDescending { it.trackName }
        }
        isSortedAscending = !isSortedAscending
        notifyDataSetChanged()
        return sortedBy
    }
}