package com.ubis.apipractice_200613.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ubis.apipractice_200613.R
import com.ubis.apipractice_200613.datas.Topic

class TopicAdapter (
    val mContext:Context,
    val resId:Int,
    val mList:List<Topic>) : ArrayAdapter<Topic>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.activity_topic_list_item, null)
        }

        val row = tempRow!!

        val TopicImageView = row.findViewById<ImageView>(R.id.topicImageView)
        val TopicTitleTxt =  row.findViewById<TextView>(R.id.topicTitleTxt)

        val data = mList[position]
        TopicTitleTxt.text = data.title

        Glide.with(mContext).load(data.imageUrl).into(TopicImageView)

        return row
    }
}