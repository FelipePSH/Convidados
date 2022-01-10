package com.example.convidados.ui.guestform.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.ui.guestform.service.model.GuestModel
import kotlinx.coroutines.internal.synchronized

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase(){


    abstract fun guestDAO(): GuestDAO



    companion object {

        private lateinit var INSTANCE: GuestDataBase
        fun getDatabase(context: Context): GuestDataBase{
            if (!::INSTANCE.isInitialized){
                kotlin.synchronized(GuestDataBase::class){
                INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestDB")
                    .allowMainThreadQueries()
                    .build()
                }
            }
            return INSTANCE
        }
    }
}
