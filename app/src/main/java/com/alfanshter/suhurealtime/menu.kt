package com.alfanshter.suhurealtime

import android.os.Bundle
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.alfanshter.suhurealtime.Model.BannerModel
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import java.util.*


class menu : AppCompatActivity(),AnkoLogger {
    var v_flipper: ViewFlipper? = null
    private var sliderLayout: SliderLayout? = null
    lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        sliderLayout = findViewById(R.id.slider_menu)
        // Load Image Dari res/drawable

        reference = FirebaseDatabase.getInstance().reference.child("Selecta").child("info/")
        reference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (i in p0.children)
                {

                  var data =  i.getValue(BannerModel::class.java)
                    // Load Image Dari res/drawable
                    val file_maps = HashMap<String, String>()
                    file_maps.put(data!!.nama + "_" , data.gambar.toString())


                    for (key in file_maps.keys)
                    {
                        val defaultSliderView = DefaultSliderView(this@menu)
                        // initialize a SliderLayout

                        defaultSliderView.image(file_maps.get(key))
                            .description("alfan")
                        sliderLayout!!.addSlider(defaultSliderView)
                    }


                }

            }

        })

        sliderLayout!!.setPresetTransformer(SliderLayout.Transformer.Accordion)
        sliderLayout!!.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        sliderLayout!!.setCustomAnimation(DescriptionAnimation())
        sliderLayout!!.setDuration(4000)

        menusuhu.setOnClickListener {
            startActivity<Suhu>()
        }

        ac.setOnClickListener {
            startActivity<AC>()
        }

        lampu.setOnClickListener {
            startActivity<lampu>()
        }

        grafik.setOnClickListener {
            startActivity<Diagram>()
        }

    }
    fun fliverImages(images: Int) {
        val imageView = ImageView(this)
        imageView.setBackgroundResource(images)
        v_flipper?.addView(imageView)
        v_flipper?.flipInterval = 4000
        v_flipper?.isAutoStart = true
        v_flipper?.setInAnimation(this, android.R.anim.slide_in_left)
        v_flipper?.setOutAnimation(this, android.R.anim.slide_out_right)
    }
}
