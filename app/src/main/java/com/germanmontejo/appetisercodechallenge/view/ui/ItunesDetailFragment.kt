package com.germanmontejo.appetisercodechallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.germanmontejo.appetisercodechallenge.R
import com.germanmontejo.appetisercodechallenge.utils.Utils
import com.germanmontejo.appetisercodechallenge.viewmodel.ItunesViewModel
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItunesDetailFragment : Fragment() {

    private lateinit var trackName: String
    private lateinit var trackPrice: String
    private lateinit var genre: String
    private var trackId: Int = 0
    private lateinit var description: String
    private lateinit var artwork: String
    private lateinit var imgArtwork: ImageView
    private lateinit var itunesViewModel: ItunesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(RESULT_BUNDLE)) {
                val bundle = it.getBundle(RESULT_BUNDLE)
                trackName = bundle?.getString(TRACK_NAME, "") ?: ""
                trackPrice = bundle?.getString(TRACK_PRICE, "") ?: ""

                genre = bundle?.getString(TRACK_GENRE, "") ?: ""
                description = bundle?.getString(LONG_DESCRIPTION, "") ?: ""
                trackId = bundle?.getInt(TRACK_ID, 0) ?: 0

                artwork = bundle?.getString(ARTWORK, "") ?: ""
                imgArtwork = activity?.findViewById(R.id.imvArtworkIcon)!!

                activity?.toolbar_layout?.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                activity?.toolbar_layout?.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
                activity?.toolbar_layout?.title = trackName
            }
        }

        itunesViewModel = ViewModelProviders.of(this).get(ItunesViewModel::class.java)
        itunesViewModel.initItunesRepo(activity!!.application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        // This means the app loaded the last viewed track details screen.
        if (trackName.isEmpty()) {
            // We're going to retrieve the last viewed track details from the local db
            // since we can't rely on the bundle would have been cleared by now if the app
            // has been force closed.
            val trackId = Utils.getLastTrackViewed(rootView.context)
            itunesViewModel.getItunesTrackById(trackId).observe(this, Observer { itunesTrack ->
                trackName = itunesTrack.trackName
                trackPrice = "${itunesTrack.currency} ${itunesTrack.trackPrice}"
                genre = itunesTrack.genre
                description = itunesTrack.description
                artwork = itunesTrack.artworkUrl
                populateViewWithTrackData(rootView)
                activity?.toolbar_layout?.title = trackName
            })
        } else {
            populateViewWithTrackData(rootView)
        }

        return rootView
    }

    fun populateViewWithTrackData(rootView: View) {
        rootView.txtTrackName.text = trackName
        rootView.txtTrackPrice.text = trackPrice
        rootView.txtGenre.text = genre
        rootView.txtDescription.text = description
        Glide.with(this)
            .load(artwork)
            .placeholder(R.mipmap.ic_launcher)
            .into(imgArtwork)
        Utils.storeLastTrackViewed(rootView.context, trackId)
    }

    companion object {
        const val TRACK_ID = "track_id"
        const val RESULT_BUNDLE = "result_bundle"
        const val TRACK_NAME = "track_name"
        const val TRACK_PRICE = "track_price"
        const val TRACK_GENRE = "genre"
        const val ARTWORK = "artwork"
        const val LONG_DESCRIPTION = "long_description"
    }
}
