package com.ubis.apipractice_200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class ViewTopicDetailActivity : baseActivity() {

    // 화면에서 넘겨준 주제 ID
    var mTopicId = -1

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

    }

    override fun setEvents() {
    }
}
