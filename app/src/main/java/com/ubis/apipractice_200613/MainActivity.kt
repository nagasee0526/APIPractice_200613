package com.ubis.apipractice_200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.ubis.apipractice_200613.adapters.TopicAdapter
import com.ubis.apipractice_200613.datas.Topic
import com.ubis.apipractice_200613.utils.ContextUtil
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : baseActivity() {

    val topicList = ArrayList<Topic>()

    lateinit var topicAdapter : TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
        setEvents()

    }

    override fun setValues() {

        getTopicListFromServer()

        topicAdapter = TopicAdapter(mContext, R.layout.activity_topic_list_item, topicList)

        topicListView.adapter = topicAdapter
    }

    fun getTopicListFromServer() {

        ServerUtils.getRequestV2MainInfo(mContext, object : ServerUtils.jsonResponceHandler{
            override fun onResponce(json: JSONObject) {

                val code = json.getInt("code")

                if( code == 200) {

                    val data = json.getJSONObject("data")

                    val topics = data.getJSONArray("topics")

                    for(i in 0..topics.length()-1){
                        val Topicjson = topics.getJSONObject(i)

                        val topic = Topic.getTopicFromJson(Topicjson)

                        topicList.add(topic)
                    }
                }


            }

        })
    }

    override fun setEvents() { //서버에서 내 정보를 받아와서 하면에 뿌려준다

        topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopic = topicList[position]
            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic_id", clickedTopic.id)
            startActivity(myIntent)
        }

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
