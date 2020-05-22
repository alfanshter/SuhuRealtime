package com.alfanshter.suhurealtime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfanshter.suhurealtime.Model.BannerModel
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.google.firebase.database.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DaimaijaFirebase : AppCompatActivity() {

    lateinit var mSlider: SliderLayout
    var image_list: HashMap<String, String>? = null
    lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daimaija_firebase)

        setupslider()
    }

    private fun setupslider() {
        mSlider = findViewById(R.id.slider)
        image_list = HashMap()
        reference = FirebaseDatabase.getInstance().reference.child("Selecta").child("info")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (data in p0.children)
                {
                    var banner = data.getValue(BannerModel::class.java)
                    image_list!![banner!!.nama.toString()+"_"+banner.id.toString()] = banner.gambar.toString()
                }

                for (key in image_list!!.keys){
                    var keysplit = key.split("_")
                    val nama = keysplit[0]
                    val id = keysplit[1]


                    val textSliderView = TextSliderView(baseContext)
                    textSliderView.description(nama)
                        .image(image_list!!.get(key))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(object : BaseSliderView.OnSliderClickListener{
                            override fun onSliderClick(slider: BaseSliderView?) {

                            }

                        })

                    //add extra bundle
                    mSlider.addSlider(textSliderView)

                    //remove banner
                    reference.removeEventListener(this)


                }
            }

        })

        mSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground)
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        mSlider.setCustomAnimation(DescriptionAnimation())
        mSlider.setDuration(4000)

    }

    override fun onStop() {
        super.onStop()
        mSlider.startAutoCycle()
    }
}
