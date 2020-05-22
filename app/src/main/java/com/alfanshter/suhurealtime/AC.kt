package com.alfanshter.suhurealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_ac.*

class AC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac)

        btn_on.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("nilai/logika3")
            myRef.setValue(1)
        })
        btn_off.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("nilai/logika3")
            myRef.setValue(0)
        })

        btn_auto.setOnClickListener(View.OnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("nilai/logika3")
            myRef.setValue(2)
        })

    }


}
