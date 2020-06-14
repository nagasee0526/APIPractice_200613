package com.ubis.apipractice_200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : baseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setEvents()
        setValues()
    }

    override fun setValues() {
        val myHandler = Handler()
        myHandler.postDelayed( {
            // 시간이 지난뒤 실행할 내용
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
        }, 3000)


    }

    override fun setEvents() {
    }
}
