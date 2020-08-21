package com.erencol.sermon.View.Activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toolbar
import com.erencol.sermon.Model.SpecialDay
import com.erencol.sermon.R;
import com.erencol.sermon.View.Adapters.SpecialDayAdapter
import com.erencol.sermon.databinding.ActivitySpecialDaysBinding
import com.erencol.sermon.viewmodelpkg.SpecialDaysViewModel

class SpecialDays : AppCompatActivity() {
    lateinit var specialDaysBinding: ActivitySpecialDaysBinding
    var specailDaysList = listOf(SpecialDay("Üç Ayların Başlangıcı","25 Şubat 2020"),
                                 SpecialDay("Regaib Kandili","27 Şubat 2020"),
                                 SpecialDay("Miraç Kandili","21 Mart 2020"),
                                 SpecialDay("Berat Kandili","7 Nisan 2020"),
                                 SpecialDay("Ramazan Başlangıcı","24 Nisan 2020"),
                                 SpecialDay("Kadir Gecesi","19 Mayıs 2020"),
                                 SpecialDay("Arefe","23 Mayıs 2020"),
                                 SpecialDay("Ramazan Bayramı 1. Gün","24 Mayıs 2020"),
                                 SpecialDay("Ramazan Bayramı 2. Gün","25 Mayıs 2020"),
                                 SpecialDay("Ramazan Bayramı 3. Gün","26 Mayıs 2020"),
                                 SpecialDay("Arefe","30 Temmuz 2020"),
                                 SpecialDay("Kurban Bayramı 1. Gün","31 Temmuz 2020"),
                                 SpecialDay("Kurban Bayramı 2. Gün","1 Ağustos 2020"),
                                 SpecialDay("Kurban Bayramı 3. Gün","2 Ağustos 2020"),
                                 SpecialDay("Kurban Bayramı 4. Gün","3 Ağustos 2020"),
                                 SpecialDay("Hicri Yılbaşı","20 Ağustos 2020"),
                                 SpecialDay("Aşure Günü","29 Ağustos 2020"),
                                 SpecialDay("Mevlid Kandili","29 Ekim 2020"),
    )
    var specialDaysViewModel: SpecialDaysViewModel = SpecialDaysViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        specialDaysBinding = DataBindingUtil.setContentView(this,R.layout.activity_special_days)
        specialDaysBinding.lifecycleOwner = this
        specialDaysBinding.specialDaysViewModel = specialDaysViewModel
        setToolBar()
        setAdapter();
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            super.onBackPressed()
            return true
        } else {
            return  super.onOptionsItemSelected(item)
        }
    }
}