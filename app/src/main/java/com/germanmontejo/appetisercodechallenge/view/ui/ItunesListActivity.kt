package com.germanmontejo.appetisercodechallenge.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.germanmontejo.appetisercodechallenge.R
import com.germanmontejo.appetisercodechallenge.model.ItunesResponse
import com.germanmontejo.appetisercodechallenge.utils.Utils
import com.germanmontejo.appetisercodechallenge.view.adapter.ItunesTrackAdapter
import com.germanmontejo.appetisercodechallenge.viewmodel.ItunesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.jetbrains.anko.doAsync
import java.text.SimpleDateFormat
import java.util.*

class ItunesListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private lateinit var itunesViewModel: ItunesViewModel
    private lateinit var itunesAdapter: ItunesTrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        itunesViewModel = ViewModelProviders.of(this).get(ItunesViewModel::class.java)
        itunesViewModel.initItunesRepo(application)
        getItunesTrack()

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            val sortedBy = itunesAdapter.sortResult()
            Snackbar.make(view, "Results sorted by: $sortedBy", Snackbar.LENGTH_LONG).show()
        }

        // If the user's last visited screen was the ItunesDetailActivity, the code below
        // would ensure that when the user opens the app again, it would load the ItunesDetailActivity.
        if (Utils.ITUNES_DETAIL_ACTIVITY.equals(Utils.getStringPref(this, Utils.LAST_ACTIVITY))) {
            val intent = Intent(this, ItunesDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Utils.storeStringPref(this, Utils.LAST_ACTIVITY, Utils.ITUNES_LIST_ACTIVITY)
        txtPreviousVisited.text = "${getString(R.string.last_visited)} ${Utils.getStringPref(this, Utils.LAST_VISITED)}"
    }

    override fun onStop() {
        super.onStop()
        Utils.storeStringPref(this, Utils.LAST_VISITED, getFormattedDateTime())
    }

    // This function returns a datetime str to be displayed
    // as a "last visited" info on the itunes track list recyclerview.
    private fun getFormattedDateTime(): String {
        val dateTime = Calendar.getInstance().time
        return SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(dateTime)
    }

    // this method is responsible for fetching the itunes tracks from the API
    // after the data is fetched, it will be populated in the recycler view.
    private fun getItunesTrack() {
        itunesViewModel.itunesTrack.observe(this, Observer { itunesResponse ->

            // No data fetched from the API, could be failure to connect to the internet.
            // We're going to repopulate the recyclerview with data from the local db
            if (itunesResponse.resultCount == 0) {
                itunesViewModel.allTracks.observe(this, Observer { allTracks ->
                    val itunesResponse = itunesViewModel.composeItunesResponse(allTracks)
                    setupRecyclerView(itunesResponse)
                })
            } else {
                setupRecyclerView(itunesResponse)
                populateTracksInDb(itunesResponse)
            }
        })
    }

    private fun populateTracksInDb(itunesResponse: ItunesResponse) {
        doAsync {
            itunesViewModel.insert(itunesResponse)
        }
    }

    private fun setupRecyclerView(itunesResponse: ItunesResponse) {
        itunesAdapter = ItunesTrackAdapter(itunesResponse.itunesResults, twoPane)
        with(rcvItunesList) {
            adapter = itunesAdapter
            layoutManager = LinearLayoutManager(this@ItunesListActivity)
        }
    }
}
