package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : baseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setEvents()
        setValues()
    }

    override fun setValues() {
    }

    override fun setEvents() {

        emailCheckBtn.setOnClickListener {
            // 입력한 이메일이 이미 회원으로 있는지 확인 => 서버에 요청

            val inputEmail = emailEdt.text.toString()



        }
    }
}