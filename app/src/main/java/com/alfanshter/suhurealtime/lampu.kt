package com.alfanshter.suhurealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_lampu.*

class lampu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lampu)

        readdata()

        btn_on.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("rumah/lampu/lampudapur")
            myRef.setValue(1)
        })
        btn_off.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("rumah/lampu/lampudapur")
        myRef.setValue(0)
        })

        btn_auto.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("rumah/lampu/lampudapur")
            myRef.setValue(2)
      
        })


    }

    fun readdata()
    {
        val reference = FirebaseDatabase.getInstance().reference.child("rumah/lampu/lampudapur")
        reference.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

             }

            override fun onDataChange(p0: DataSnapshot) {
                var nilai = p0.value.toString().toInt()
                if (nilai ==0)
                {
                    kondisilampu.text = "LAMPU DAPUR MATI"
                }
                else if (nilai ==1)
                {
                    kondisilampu.text = "LAMPU DAPUR NYALA"
                }
                else if (nilai==2)
                {
                    kondisilampu.text = "LAMPU DAPUR OTOMATIS BERDASARKAN WAKTU"
                }
             }

        })
    }

}
