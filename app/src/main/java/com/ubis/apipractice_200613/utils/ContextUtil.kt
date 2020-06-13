package com.ubis.apipractice_200613.utils

import android.content.Context

class ContextUtil {

    companion object{
        val prefName = "APIPracticePreference"

        val USER_TOCKEN = "USER_TOCKEN"

        // 토큰 저장 기능
        fun setUserTocken(context: Context, tocken:String) {
            //저장할때 사용할 메모장 파일을 열자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            // 열어준 메모장의 USER_TOCKEN항목에 받아온 Tocken에 든값을 저장
            pref.edit().putString(USER_TOCKEN, tocken).apply()
        }

    }

}