package com.ubis.apipractice_200613

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ubis.apipractice_200613.utils.ContextUtil
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
        // 자동로그인의 여부를 contextUtils에서 가져와 checkBox로 설정한다.
        autoLoginCheckBox.isChecked = ContextUtil.isAutoLogin(mContext)
    }

    override fun setEvents() {

        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            // isChexked에는 지금 어떤 상태가 되었는지 Boolean 으로 들어옴
            ContextUtil.setAutoLogin(mContext, isChecked)
        }

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
                val message = json.getString("message")

                if( codeNum == 200) { // 로그인 성공

                    val data = json.getJSONObject("data")
                    val token = data.getString("token")

                    ContextUtil.setUserTocken(mContext, token)
//                        로그인 성공 => 메인액티비티로 이동

                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                }
                else { // 로그인 실패 - 실패사유 message에 적힌 사유 확인
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
