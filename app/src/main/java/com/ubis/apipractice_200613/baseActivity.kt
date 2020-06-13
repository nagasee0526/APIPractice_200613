package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity

abstract class baseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setValues()
    abstract fun setEvents()
}