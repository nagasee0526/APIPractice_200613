package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.util.*

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

            ServerUtils.getRequestDuplicationCheck(mContext, "EMAIL", inputEmail, object : ServerUtils.jsonResponceHandler{
                override fun onResponce(json: JSONObject) {

                    val code = json.getInt("code")

                    if( code == 200 ){
                        runOnUiThread {
                            emailCheckResultTxt.text = "사용해도 좋습니다."
                        }
                    }
                    else {
                        runOnUiThread {
                            emailCheckResultTxt.text = "이미 사용중입니다. 다른 이메일을 입력해 주세요."
                        }

                    }

                }

            })
        }

        nickNameCheckBtn.setOnClickListener {
            val inputNick = nickNameEdt.text.toString()

            ServerUtils.getRequestDuplicationCheck(mContext, "NICKNAME", inputNick, object : ServerUtils.jsonResponceHandler{
                override fun onResponce(json: JSONObject) {

                    val code = json.getInt("code")

                    if( code == 200 ){
                        runOnUiThread {
                            nickNameCheckResultTxt.text = "사용해도 좋습니다."
                        }
                    }
                    else {
                        runOnUiThread {
                            nickNameCheckResultTxt.text = "이미 사용중입니다. 다른 닉네임을 입력해 주세요."
                        }

                    }

                }

            })
        }

        nickNameEdt.addTextChangedListener (object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nickNameCheckResultTxt.text = "중복확인을 해주세요"
//                 Log.d("변경된내용", "${s}") // 변경된 문자열 출력
            }

        })

        emailEdt.addTextChangedListener (object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nickNameCheckResultTxt.text = "중복확인을 해주세요"
            }

        })

        signUpBtn.setOnClickListener {

        }
    }
}
