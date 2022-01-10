package com.example.convidados.ui.guestform.service.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.convidados.ui.guestform.service.model.GuestModel

class GuestRepository (context: Context) {

    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()

    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    @SuppressLint("Range")
    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    @SuppressLint("Range")
    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }


    fun getUser(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    //CRUD - CREATE, READ, UPDATE, DELETE


    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 1
    }

    fun delete(guest: GuestModel) {
        return mDataBase.delete(guest)
    }


}
