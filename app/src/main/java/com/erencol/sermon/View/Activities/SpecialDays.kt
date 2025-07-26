package com.erencol.sermon.View.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
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
    val specialDaysList = listOf(
        SpecialDay("Üç Ayların Başlangıcı", "1 Ocak 2025"),
        SpecialDay("Regaib Kandili", "2 Ocak 2025"),
        SpecialDay("Miraç Kandili", "26 Ocak 2025"),
        SpecialDay("Berat Kandili", "13 Şubat 2025"),
        SpecialDay("Ramazan Başlangıcı", "1 Mart 2025"),
        SpecialDay("Kadir Gecesi", "26 Mart 2025"),
        SpecialDay("Arefe", "29 Mart 2025"),
        SpecialDay("Ramazan Bayramı 1. Gün", "30 Mart 2025"),
        SpecialDay("Ramazan Bayramı 2. Gün", "31 Mart 2025"),
        SpecialDay("Ramazan Bayramı 3. Gün", "1 Nisan 2025"),
        SpecialDay("Arefe", "5 Haziran 2025"),
        SpecialDay("Kurban Bayramı 1. Gün", "6 Haziran 2025"),
        SpecialDay("Kurban Bayramı 2. Gün", "7 Haziran 2025"),
        SpecialDay("Kurban Bayramı 3. Gün", "8 Haziran 2025"),
        SpecialDay("Kurban Bayramı 4. Gün", "9 Haziran 2025"),
        SpecialDay("Hicri Yılbaşı", "26 Haziran 2025"),
        SpecialDay("Aşure Günü", "5 Temmuz 2025"),
        SpecialDay("Mevlid Kandili", "3 Eylül 2025"),
        SpecialDay("Üç Ayların Başlangıcı", "21 Aralık 2025"),
        SpecialDay("Regaib Kandili", "25 Aralık 2025"),
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
        setSupportActionBar(specialDaysBinding.toolbar as Toolbar?)
        supportActionBar?.title = resources.getString(R.string.special_days_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setAdapter(){
        val adapter= SpecialDayAdapter()
        specialDaysBinding.specialdaysrecyclerview.adapter = adapter
        specialDaysBinding.specialdaysrecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.setSpecialDays(specialDaysList)

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