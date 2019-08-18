package com.germanmontejo.appetisercodechallenge.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItunesTrack::class], version = 1)
public abstract class ItunesDatabase : RoomDatabase() {
    abstract fun itunesDao(): ItunesDao


    companion object {
        @Volatile
        private var INSTANCE: ItunesDatabase? = null

        fun getDatabase(context: Context): ItunesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItunesDatabase::class.java,
                    "itunes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}