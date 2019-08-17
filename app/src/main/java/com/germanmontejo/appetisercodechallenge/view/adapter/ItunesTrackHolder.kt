package com.germanmontejo.appetisercodechallenge.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ItunesResult
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.germanmontejo.appetisercodechallenge.R
import com.germanmontejo.appetisercodechallenge.view.ui.ItunesDetailActivity
import com.germanmontejo.appetisercodechallenge.view.ui.ItunesDetailFragment
import kotlinx.android.synthetic.main.item_row_content.view.*

class ItunesTrackHolder(view: View, isTwoPane: Boolean) : RecyclerView.ViewHolder(view) {
    private val itunesView = view
    private val twoPane = isTwoPane
    private val context = itunesView.context

    fun bindViewHolder(result: ItunesResult) {
        itunesView.txtGenre.text = result.primaryGenreName
        itunesView.txtTrackName.text = result.trackName
        itunesView.txtTrackPrice.text = "${result.currency}${result.trackPrice.toString()}"
        Glide.with(context)
            .load(result.artworkUrl100)
            .placeholder(R.mipmap.ic_launcher)
            .into(itunesView.imgArtwork)

        Log.d("itemlist", "isTwoPane: ${twoPane}")
        itemView.setOnClickListener {
            if (twoPane) {

            } else {
                // start ItunesDetailActivity
                val intent = Intent(context, ItunesDetailActivity::class.java)
                intent.putExtra(ItunesDetailFragment.RESULT_BUNDLE, setupBundle(result))
                context.startActivity(intent)
            }
        }
    }

    private fun setupBundle(result: ItunesResult): Bundle {
        val bundle = Bundle()
        bundle.putString(ItunesDetailFragment.TRACK_NAME, result.trackName)
        bundle.putString(ItunesDetailFragment.TRACK_PRICE, "${result.currency} ${result.trackPrice}")

        bundle.putString(ItunesDetailFragment.TRACK_GENRE, result.primaryGenreName)
        bundle.putString(ItunesDetailFragment.ARTWORK, result.artworkUrl100)
        bundle.putString(ItunesDetailFragment.LONG_DESCRIPTION, result.longDescription)
        return bundle
    }
}