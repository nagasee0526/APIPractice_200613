package com.ubis.apipractice_200613.datas

import org.json.JSONObject

class Topic {

    companion object {
        fun getTopicFromJson(json : JSONObject) : Topic {
            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title =  json.getString("title")
            topic.imageUrl = json.getString("img_url")

            return topic
        }

    }

    var id = 0
    var title = ""
    var imageUrl = ""


}