package com.ubis.apipractice_200613

import android.os.Bundle
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : baseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setEvents()
        setValues()
    }

    override fun setValues() {
    }

    override fun setEvents() {

        loginBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()
        // 실제로 서버에서 두개의 변수를 전달해서 로그인 시도
        // 별개의 클래스 (ServerUtil)에 서버요철기능을 만들고 화면에서 이를 사용

        ServerUtils.postRequestLogin(mContext, inputEmail, inputPw, null)
        }
    }

}
