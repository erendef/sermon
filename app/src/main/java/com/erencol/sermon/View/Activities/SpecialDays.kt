package com.erencol.sermon.View.Activities

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import com.erencol.sermon.Model.SpecialDay
import com.erencol.sermon.R;
import com.erencol.sermon.View.Adapters.SpecialDayAdapter
import com.erencol.sermon.databinding.ActivitySpecialDaysBinding
import com.erencol.sermon.viewmodelpkg.SpecialDaysViewModel
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory

class SpecialDays : AppCompatActivity() {
    private val ACTIVITY_CALLBACK = 1
    private var reviewInfo: ReviewInfo? = null
    private lateinit var reviewManager: ReviewManager
    lateinit var specialDaysBinding: ActivitySpecialDaysBinding
    var specailDaysList = listOf(SpecialDay("Üç Ayların Başlangıcı","13 Şubat 2021"),
                                 SpecialDay("Regaib Kandili","18 Şubat 2021"),
                                 SpecialDay("Miraç Kandili","10 Mart 2021"),
                                 SpecialDay("Berat Kandili","27 Mart 2021"),
                                 SpecialDay("Ramazan Başlangıcı","13 Nisan 2021"),
                                 SpecialDay("Kadir Gecesi","8 Mayıs 2021"),
                                 SpecialDay("Arefe","12 Mayıs 2021"),
                                 SpecialDay("Ramazan Bayramı 1. Gün","13 Mayıs 2021"),
                                 SpecialDay("Ramazan Bayramı 2. Gün","14 Mayıs 2021"),
                                 SpecialDay("Ramazan Bayramı 3. Gün","15 Mayıs 2021"),
                                 SpecialDay("Arefe","19 Temmuz 2021"),
                                 SpecialDay("Kurban Bayramı 1. Gün","20 Temmuz 2021"),
                                 SpecialDay("Kurban Bayramı 2. Gün","21 Temmuz 2021"),
                                 SpecialDay("Kurban Bayramı 3. Gün","22 Temmuz 2021"),
                                 SpecialDay("Kurban Bayramı 4. Gün","23 Temmuz 2021"),
                                 SpecialDay("Hicri Yılbaşı","9 Ağustos 2021"),
                                 SpecialDay("Aşure Günü","18 Ağustos 2021"),
                                 SpecialDay("Mevlid Kandili","17 Ekim 2021"),
                                 SpecialDay("Mevlid Kandili","17 Ekim 2021"),
    )
    var specialDaysViewModel: SpecialDaysViewModel = SpecialDaysViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        specialDaysBinding = DataBindingUtil.setContentView(this,R.layout.activity_special_days)
        specialDaysBinding.lifecycleOwner = this
        specialDaysBinding.specialDaysViewModel = specialDaysViewModel
        setToolBar()
        setAdapter()
        reviewGooglePlayReviewInterface()
    }

    fun reviewGooglePlayReviewInterface() {

        //Create the ReviewManager instance
        reviewManager = ReviewManagerFactory.create(this)

        //Request a ReviewInfo object ahead of time (Pre-cache)
        val requestFlow = reviewManager.requestReviewFlow()
        requestFlow.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                //Received ReviewInfo object
                reviewInfo = request.result
            } else {
                //Problem in receiving object
                reviewInfo = null
            }
        }

    }

    fun setToolBar (){
        setSupportActionBar(specialDaysBinding.toolbar)
        supportActionBar?.setTitle(resources.getString(R.string.special_days_title))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setAdapter(){
        val adapter= SpecialDayAdapter()
        specialDaysBinding.specialdaysrecyclerview.adapter = adapter
        specialDaysBinding.specialdaysrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        adapter.setSpecialDays(specailDaysList)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_CALLBACK && resultCode == Activity.RESULT_OK) {
            Handler().postDelayed({
                reviewInfo?.let {
                    val flow = reviewManager.launchReviewFlow(this@SpecialDays, it)
                    flow.addOnSuccessListener {
                        //Showing toast is only for testing purpose, this shouldn't be implemented
                        //in production app.
                        Toast.makeText(
                                this@SpecialDays,
                                "Thanks for the feedback!",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                    flow.addOnFailureListener {
                        //Showing toast is only for testing purpose, this shouldn't be implemented
                        //in production app.
                        Toast.makeText(this@SpecialDays, "${it.message}", Toast.LENGTH_LONG).show()
                    }
                    flow.addOnCompleteListener {
                        //Showing toast is only for testing purpose, this shouldn't be implemented
                        //in production app.
                        Toast.makeText(this@SpecialDays, "Completed!", Toast.LENGTH_LONG).show()
                    }
                }
            }, 3000)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home){
            super.onBackPressed()
            return true
        } else {
            return  super.onOptionsItemSelected(item)
        }
    }
}