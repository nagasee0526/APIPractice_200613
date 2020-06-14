package com.ubis.apipractice_200613.utils

import android.content.Context

class ContextUtil {

    companion object{
        //메모장 파일이름에 대응하는 개념의 변수
        val prefName = "APIPracticePreference"

        // 저장될 데이터 항목 이름들을 지정
        val USER_TOCKEN = "USER_TOCKEN"

        // 토큰 저장 기능
        fun setUserTocken(context: Context, tocken:String) {
            //저장할때 사용할 메모장 파일을 열자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            // 열어준 메모장의 USER_TOCKEN항목에 받아온 Tocken에 든값을 저장
            pref.edit().putString(USER_TOCKEN, tocken).apply()
        }

        fun getUserTocken(context: Context) : String {
            //저장할때 사용한 메모장 파일을 열자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            // 열어준 메모장의 USER_TOCKEN항목에 저장된 tocken값을 꺼내서 리턴
            return pref.getString(USER_TOCKEN, "")!!
        }

    }

}