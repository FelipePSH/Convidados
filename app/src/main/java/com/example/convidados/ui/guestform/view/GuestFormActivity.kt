package com.example.convidados.ui.guestform.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.ui.guestform.viewmodel.GuestFormViewModel
import com.example.convidados.ui.home.service.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)
        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radio_presence.isChecked = true
    }


    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.btn_save) {

            val name = edt_guest_name.text.toString()
            val presence = radio_presence.isChecked


            mViewModel.save(mGuestId, name, presence)

        }

    }


    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "falha", Toast.LENGTH_SHORT).show()

            }

            finish()
        }
        )

        mViewModel.guest.observe(this, Observer {
            edt_guest_name.setText(it.name)
            if (it.presence) {
                radio_presence.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })
    }


    private fun setListeners() {
        btn_save.setOnClickListener(this)
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUEST_ID)
            //carregando user
            mViewModel.load(mGuestId)
        }
    }


}                                             
