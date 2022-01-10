package com.example.convidados.ui.absents.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.databinding.FragmentAbsentBinding
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.ui.guestform.view.GuestFormActivity
import com.example.convidados.ui.home.service.GuestConstants
import com.example.convidados.ui.home.view.adapter.GuestAdapter
import com.example.convidados.ui.home.view.listener.GuestListener

import com.example.convidados.ui.home.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {
    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    private var _binding: FragmentAbsentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel =
                ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //RecyclerView
        // 1 - Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_absent)
        // 2 - Definir layout
        recycler.layoutManager = LinearLayoutManager(root.context)
        // 3 - Definir um Adapter
        recycler.adapter = mAdapter


        mListener = object : GuestListener {
            override fun onClick(id: Int) {


                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID, id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.ABSENT)

                Toast.makeText(context, "Convidado Deletado", Toast.LENGTH_SHORT).show()
            }
        }

        mAdapter.attachListener(mListener)

        observer()


        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.ABSENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}
