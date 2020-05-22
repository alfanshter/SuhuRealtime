package com.alfanshter.suhurealtime

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.*
import com.pusher.pushnotifications.PushNotifications
import kotlinx.android.synthetic.main.activity_temperature.*
import org.jetbrains.anko.toast

class Temperature : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelID = "com.alfanshter.suhurealtime"
    private val description = "Test notification"

    private lateinit var dref: DatabaseReference
    private lateinit var key:String

    var status = 0f
    var status2 = 0f
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)

        PushNotifications.start(applicationContext, "ca294a3b-8615-452a-83a7-a815fb3f4dd4")
        PushNotifications.addDeviceInterest("hello")


        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        dref = FirebaseDatabase.getInstance().reference
        dref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                status = dataSnapshot.child("lokasi/latidude").value!!.toString().toFloat()
                status2 = dataSnapshot.child("Temperature/Nilai Kelembapan :").value!!.toString().toFloat()

                toast("nilai :${status}")
                txttemperature.text = "${status}"
                txthumidity.text = "${status2}"


                if (status > 29)
                {
                    val intent = Intent(this@Temperature,LauncherActivity::class.java)
                    val pendingIntent = PendingIntent.getActivity(this@Temperature,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        notificationChannel = NotificationChannel(channelID,description,NotificationManager.IMPORTANCE_HIGH)
                        notificationChannel.enableLights(true)
                        notificationChannel.lightColor = Color.GREEN
                        notificationChannel.enableVibration(false)
                        notificationManager.createNotificationChannel(notificationChannel)

                        builder = Notification.Builder(this@Temperature,channelID)
                            .setContentTitle("ALFAN")
                            .setContentText("mukhammad")
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setLargeIcon(BitmapFactory.decodeResource(this@Temperature.resources,R.mipmap.ic_launcher))
                            .setContentIntent(pendingIntent)
                    }
                    notificationManager.notify(1234,builder.build())

                }

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })




    }
    }


