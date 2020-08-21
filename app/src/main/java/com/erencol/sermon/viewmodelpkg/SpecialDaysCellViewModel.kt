package com.erencol.sermon.viewmodelpkg

import android.databinding.BaseObservable
import com.erencol.sermon.Model.SpecialDay

class SpecialDaysCellViewModel(specialDay: SpecialDay): BaseObservable(){

    var specialDayModel: SpecialDay = specialDay
        set(value) {
            field=value
        }

    fun getDayName():String{
        return specialDayModel.dayName
    }

    fun getDayDate():String {
        return specialDayModel.dayDate
    }
}