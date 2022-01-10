package com.example.convidados.ui.home.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.ui.guestform.service.model.GuestModel
import com.example.convidados.ui.home.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {


    fun bind(guest: GuestModel) {
        val guestName = itemView.findViewById<TextView>(R.id.text_guest_name)
        guestName.text = guest.name

        guestName.setOnClickListener {
            listener.onClick(guest.id)
        }

        guestName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context).setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, which ->
                    listener.onDelete(guest.id)
                }.setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }

}
