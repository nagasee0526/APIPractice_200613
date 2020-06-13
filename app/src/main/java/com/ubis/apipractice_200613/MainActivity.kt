package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : baseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
        setEvents()
    }

    override fun setValues() {
    }

    override fun setEvents() { //서버에서 내 정보를 받아와서 하면에 뿌려준다

    }
}
