package com.alfanshter.suhurealtime.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alfanshter.suhurealtime.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.support.v4.toast

class DashboardFragment : Fragment() {
    private lateinit var dref: DatabaseReference
    private lateinit var key:String
    var status = 0f
    var status2 = 0f


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dref = FirebaseDatabase.getInstance().reference
        dref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                status = dataSnapshot.child("Temperature/Nilai Suhu :").value!!.toString().toFloat()
                status2 = dataSnapshot.child("Temperature/Nilai Kelembapan :").value!!.toString().toFloat()
                textsuhu?.text = "${status}"
                toast("nilai ${status}")

                if (status > 30)
                {
                    kondisisuhu?.text="Suhu Hangat"
                }
                else if (status < 20)
                {
                    kondisisuhu?.text="Suhu Dingin"
                }

                else if (status >=20 || status <=29)
                {
                    kondisisuhu?.text="Suhu Normal"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
}