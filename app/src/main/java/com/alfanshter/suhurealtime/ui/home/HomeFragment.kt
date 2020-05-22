package com.alfanshter.suhurealtime.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import android.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*


class HomeFragment : Fragment() {
    private lateinit var dref: DatabaseReference
    private lateinit var key:String
    var status2 = 0f
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dref = FirebaseDatabase.getInstance().reference
        dref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                status2 = dataSnapshot.child("Temperature/Nilai Kelembapan :").value!!.toString().toFloat()
                texthum?.text = "${status2}"

                if (status2 > 80)
                {
                    kondisihum?.text="Kelembapan Basah"
                }
                else if (status2 < 30)
                {
                    kondisihum?.text="Kelembapan Kering"
                }

                else if (status2 >=30 || status2 <=80)
                {
                    kondisihum?.text="Kelembapan Normal"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }

        })

    }

/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_on.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Notifikasi/Nilai Logika :")
            myRef.setValue(1)
        })
       btn_off.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Notifikasi/Nilai Logika :")
            myRef.setValue(0)
        })
    }
*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.alfanshter.suhurealtime.R.layout.fragment_home, container, false)    }

}