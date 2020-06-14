package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ubis.apipractice_200613.datas.Topic
import com.ubis.apipractice_200613.utils.ServerUtils
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : baseActivity() {

    // 화면에서 넘겨준 주제 ID
    var mTopicId = -1

    lateinit var mTopic : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setEvents()
        setValues()
    }

    override fun setValues() {


         mTopicId = intent.getIntExtra("topic_id", -1)

        if( mTopicId == -1){
            Toast.makeText(mContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("넘겨받은 주제 아이디", mTopicId.toString())
        // 넘겨받은 아이디값으로 서버에서 해당 주제를 불러온다

        ServerUtils.getRequestTopicDetail(mContext, mTopicId, object : ServerUtils.jsonResponceHandler{
            override fun onResponce(json: JSONObject) {

                val code = json.getInt("code")

                if(code == 200)
                {
                    val data = json.getJSONObject("data")
                    val topic = data.getJSONObject("topic")

                    mTopic = Topic.getTopicFromJson(topic)

                    runOnUiThread {
                        topicTitleTxt.text = mTopic.title

                        Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

                        firstSideTxt.text = mTopic.sides[0].title
                        firstSideTxt.text = mTopic.sides[1].title

                        firstSideVoteCountTxt.text = "${mTopic.sides[0].voteCount}표"
                        secondSideVoteCountTxt.text = "${mTopic.sides[1].voteCount}표"
                    }
                }

            }

        })

    }

    override fun setEvents() {

        voteToFirstBtn.setOnClickListener {
            ServerUtils.postRequestVote(mContext, mTopic.sides[1].id, object : ServerUtils.jsonResponceHandler{
                override fun onResponce(json: JSONObject) {

                    val code = json.getInt("code")

                    if(code == 200)
                    {
                        runOnUiThread {
                            Toast.makeText(mContext, "투표에 참여해주셔서 감사합니다." , Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message , Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            })
        }

        voteToScondBtn.setOnClickListener {
            ServerUtils.postRequestVote(mContext, mTopic.sides[0].id, object : ServerUtils.jsonResponceHandler{
                override fun onResponce(json: JSONObject) {

                    val code = json.getInt("code")

                    if(code == 200)
                    {
                        runOnUiThread {
                            Toast.makeText(mContext, "투표에 참여해주셔서 감사합니다." , Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message , Toast.LENGTH_SHORT).show()
                        }
                    }


                }

            })
        }
    }
}
