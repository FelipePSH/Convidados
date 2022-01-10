package com.example.convidados.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.ui.guestform.service.model.GuestModel
import com.example.convidados.ui.guestform.service.repository.GuestRepository
import com.example.convidados.ui.home.service.GuestConstants

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val mGuestRepository = GuestRepository(application.applicationContext)
    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList


    fun load(filter: Int) {
        when (filter) {
            GuestConstants.FILTER.EMPTY -> {
                mGuestList.value = mGuestRepository.getAll()
            }
            GuestConstants.FILTER.PRESENT -> {
                mGuestList.value = mGuestRepository.getPresent()
            }
            else -> {
                mGuestList.value = mGuestRepository.getAbsent()
            }
        }
    }

    fun delete(id: Int) {
        val guest = mGuestRepository.getUser(id)
        mGuestRepository.delete(guest)
    }
}
