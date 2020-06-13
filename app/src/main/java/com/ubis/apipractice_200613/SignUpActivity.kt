package com.ubis.apipractice_200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.util.*

class SignUpActivity : baseActivity() {

    var isEmailDuplOk = false
    var isNickNameDuplOk = false

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
                        isEmailDuplOk = true
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
                        isNickNameDuplOk = true
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
                isNickNameDuplOk = false
//                 Log.d("변경된내용", "${s}") // 변경된 문자열 출력
            }

        })

        emailEdt.addTextChangedListener (object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailCheckResultTxt.text = "중복확인을 해주세요"
                isEmailDuplOk = false
            }

        })

        signUpBtn.setOnClickListener {
            Log.d("message", "버튼 눌림")
            // 이메일 중복검사를 통과? + 닉네임 중복검사를 통과?
            if(!isEmailDuplOk) {
                runOnUiThread {
                    Log.d("message", "이메일 중복")
                    Toast.makeText(mContext, "이메일 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }

            if(!isNickNameDuplOk)
            {
                runOnUiThread {
                    Log.d("message", "닉네임 중복")
                    Toast.makeText(mContext, "닉네임 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }
            //  이메일 닉네임 확인 통과
            // 서버에 가입신청
            val email = emailEdt.text.toString()
            val pw = passwordEdt.text.toString()
            val nickname = nickNameEdt.text.toString()

            // 서버에 /user - put으로 요청 서버에 요청
            ServerUtils.putRequestSigUp(mContext, email, pw, nickname, object : ServerUtils.jsonResponceHandler {
                override fun onResponce(json: JSONObject) {

                    val code = json.getInt("code")
                    val message = json.getInt("message")

                    if( code == 200 ){ // 회원 가입 성공
                        runOnUiThread {
                            Toast.makeText(mContext, "가입 성공", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
                    else { //  회원 가입 실패
                        runOnUiThread {
                            Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })





        }



    }
}
