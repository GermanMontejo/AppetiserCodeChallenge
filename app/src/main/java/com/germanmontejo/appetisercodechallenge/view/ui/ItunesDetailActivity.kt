package com.germanmontejo.appetisercodechallenge.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.germanmontejo.appetisercodechallenge.R
import com.germanmontejo.appetisercodechallenge.utils.Utils
import kotlinx.android.synthetic.main.activity_item_detail.*

// This activity represents the UI of the selected itunes movie
class ItunesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItunesDetailFragment().apply {
                arguments = Bundle().apply {
                    putBundle(
                        ItunesDetailFragment.RESULT_BUNDLE,
                        intent.getBundleExtra(ItunesDetailFragment.RESULT_BUNDLE)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }

    }

    override fun onResume() {
        super.onResume()
        Utils.storeStringPref(this, Utils.LAST_ACTIVITY, Utils.ITUNES_DETAIL_ACTIVITY)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // this ensures that when the user presses the back button
        // we won't be automatically redirected back to the ItunesDetailActivity
        // since we have the previous logic where we should store the last visited activity
        Utils.storeStringPref(this, Utils.LAST_ACTIVITY, Utils.ITUNES_LIST_ACTIVITY)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                Utils.storeStringPref(this, Utils.LAST_ACTIVITY, Utils.ITUNES_LIST_ACTIVITY)
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
