package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
