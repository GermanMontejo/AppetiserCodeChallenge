package com.germanmontejo.appetisercodechallenge.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_detail.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.germanmontejo.appetisercodechallenge.R
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItunesListActivity]
 * in two-pane mode (on tablets) or a [ItunesDetailActivity]
 * on handsets.
 */

class ItunesDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    // private var item: DummyContent.DummyItem? = null
    private lateinit var trackName: String
    private lateinit var trackPrice: String
    private lateinit var genre: String
    private lateinit var description: String
    private lateinit var artwork: String
    private lateinit var imgArtwork: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            Log.d("itemlist", "it.containsKey(RESULT_BUNDLE): ${it.containsKey(RESULT_BUNDLE)}")
            if (it.containsKey(RESULT_BUNDLE)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                val bundle = it.getBundle(RESULT_BUNDLE)
                trackName = bundle?.getString(TRACK_NAME, "") ?: ""
                trackPrice = bundle?.getString(TRACK_PRICE, "") ?: ""
                genre = bundle?.getString(TRACK_GENRE, "") ?: ""
                description = bundle?.getString(LONG_DESCRIPTION, "") ?: ""
                Log.d("itemlist", "txtTrackName: $trackPrice")
                artwork = bundle?.getString(ARTWORK, "") ?: ""

                imgArtwork = activity?.findViewById(R.id.imvArtworkIcon)!!
                activity?.toolbar_layout?.title = trackName
                activity?.toolbar_layout?.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                activity?.toolbar_layout?.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        rootView.txtTrackName.text = trackName
        rootView.txtTrackPrice.text = trackPrice
        rootView.txtGenre.text = genre
        rootView.txtDescription.text = description
        Glide.with(this)
            .load(artwork)
            .placeholder(R.mipmap.ic_launcher)
            .into(imgArtwork)
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val RESULT_BUNDLE = "result_bundle"
        const val TRACK_NAME = "track_name"
        const val TRACK_PRICE = "track_price"
        const val TRACK_GENRE = "genre"
        const val ARTWORK = "artwork"
        const val LONG_DESCRIPTION = "long_description"
    }
}
