package com.ubis.apipractice_200613

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

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

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        loginBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()
        // 실제로 서버에서 두개의 변수를 전달해서 로그인 시도
        // 별개의 클래스 (ServerUtil)에 서버요철기능을 만들고 화면에서 이를 사용

        ServerUtils.postRequestLogin(mContext, inputEmail, inputPw, object : ServerUtils.jsonResponceHandler{
            override fun onResponce(json: JSONObject) {
              //  Log.d("화면에서 보는 응답",  json.toString())
                //응답내용을 분석 => 화면에 표현해주자

                // 제일큰 중괄호에 달린 code라는 이름의 Int를 받아서 codeNum변수에 대입
                val codeNum = json.getInt("code")

                if( codeNum == 200) { // 로그인 성공

                    val mData =json.getJSONObject("data")
                    val mUser = mData.getJSONObject("user")
                    val mNick = mUser.getString("nick_name")

                    runOnUiThread {
                        Toast.makeText(mContext, "${mNick} 님 환영합니다.", Toast.LENGTH_SHORT).show()
                    }

                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                }
                else { // 로그인 실패 - 실패사유 message에 적힌 사유 확인
                    val message =json.getString("message")

                    // 인터넷 연결 쓰레드가 아닌  UI 담당 쓰레드가 Toast를 띄운다
                    runOnUiThread {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

                }
            }

        })
        }
    }

}
