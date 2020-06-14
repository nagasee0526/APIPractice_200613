package com.ubis.apipractice_200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ubis.apipractice_200613.utils.ContextUtil
import com.ubis.apipractice_200613.utils.ServerUtils
import org.json.JSONObject
import java.util.*

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
            // 자동로그인 => 안한다면 무조건 로그인 화면
            // 한다고 하면 => 저장된 토큰이 있나? => 있다면 => 서버에서 사용자 정보를 가져온다.
            // 정보를 가져오기까지 성공하면 메인화면으로 ( 토큰의 유효성)

            if(ContextUtil.isAutoLogin(mContext)){
                if(ContextUtil.getUserTocken(mContext) != ""){
                    ServerUtils.getRequestMyInfo(mContext, object :ServerUtils.jsonResponceHandler{
                        override fun onResponce(json: JSONObject) {
                            val code = json.getInt("code")
                            if(code == 200)
                            {
                                val myIntent = Intent(mContext, MainActivity::class.java)
                                startActivity(myIntent)
                                finish()
                            }
                        }

                    })
                }
                else {
                    val myIntent = Intent(mContext, LoginActivity::class.java)
                    startActivity(myIntent)
                    finish()
                }
            }
            else{
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }



        }, 3000)

    }

    override fun setEvents() {
    }
}
