package com.ubis.apipractice_200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ubis.apipractice_200613.utils.ContextUtil
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

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

        logOutBtn.setOnClickListener {
        // 정말 로그아웃할것인지 물어본다

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", { dialog, which ->
                // 토큰을 삭제 한다. => 빈칸으로 변경
                ContextUtil.setUserTocken(mContext, "")
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }

        ServerUtils.getRequestMyInfo(mContext, object : ServerUtils.jsonResponceHandler{
            override fun onResponce(json: JSONObject) {

                val data = json.getJSONObject("data")
                val user = data.getJSONObject("user")
                val nickName = user.getString("nick_name")

                runOnUiThread {
                    loginUserNickName.text = "${nickName}님 환영합니다."
                }

            }

        })
    }
}
