package com.germanmontejo.appetisercodechallenge.utils

import android.content.Context
import com.germanmontejo.appetisercodechallenge.view.ui.ItunesDetailActivity
import com.germanmontejo.appetisercodechallenge.view.ui.ItunesListActivity

class Utils {
    companion object {
        var ITUNES_LIST_ACTIVITY = ItunesListActivity::class.java.name
        var ITUNES_DETAIL_ACTIVITY = ItunesDetailActivity::class.java.name
        private const val ITUNES_PREF = "itunes_pref"
        private const val ID = "track_id"
        private const val LAST_ACTIVITY = "last_activity"

        fun storeLastActivity(context: Context, activity: String) {
            val pref = context.getSharedPreferences(ITUNES_PREF, Context.MODE_PRIVATE).edit()
            pref.putString(LAST_ACTIVITY, activity).apply()
        }

        fun getLastActivity(context: Context): String {
            return context.getSharedPreferences(ITUNES_PREF, Context.MODE_PRIVATE).getString(LAST_ACTIVITY, "") ?: ""
        }

        fun storeLastTrackViewed(context: Context, id: Int) {
            context.getSharedPreferences(ITUNES_PREF, Context.MODE_PRIVATE).edit().putInt(ID, id).apply()
        }

        fun getLastTrackViewed(context: Context): Int {
            return context.getSharedPreferences(ITUNES_PREF, Context.MODE_PRIVATE).getInt(ID, 0)
        }

    }
}