package com.ubis.apipractice_200613.datas

import android.util.Log
import org.json.JSONObject

class TopicSide {

    // Topicside로 변환해주는 기능
    companion object{

        fun getTopicSideFromJson(json: JSONObject) : TopicSide {

            val ts = TopicSide()

            ts.id = json.getInt("id")
            ts.topicId = json.getInt("topic_id")
            ts.title = json.getString("title")
            ts.voteCount = json.getInt("vote_count")

            return ts
        }
    }

    var id = 0
    var topicId = 0
    var title = ""
    var voteCount = 0

}