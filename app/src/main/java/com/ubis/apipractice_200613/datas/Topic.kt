package com.ubis.apipractice_200613.datas

import android.util.Log
import org.json.JSONObject

class Topic {

    companion object {
        fun getTopicFromJson(json : JSONObject) : Topic {
            val topic = Topic()

            topic.id = json.getInt("id")
            topic.title =  json.getString("title")
            topic.imageUrl = json.getString("img_url")

            val sides = json.getJSONArray("sides")

            for(i in 0..sides.length()-1){
                val sidejson = sides.getJSONObject(i)

                val side = TopicSide.getTopicSideFromJson(sidejson)

                topic.sides.add(side)
            }

            return topic
        }

    }

    var id = 0
    var title = ""
    var imageUrl = ""
    val sides = ArrayList<TopicSide>()


}