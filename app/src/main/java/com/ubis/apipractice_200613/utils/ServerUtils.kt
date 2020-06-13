package com.ubis.apipractice_200613.utils

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.sql.ClientInfoStatus

class ServerUtils {

    // 어느 객체인지 관계없이 가능/값만 잘 사용하면 되는것을 모아둔 영역
    companion object{
        // 어느서버로 가야하는지 적어두는 변수
        val BASE_URL = "http://15.165.177.142"

        //서버에 로그인 요청해주는 함수
        // context / handler 는 필수로 넣어두자
        // 둘사이에 화면에서 넘겨줘야 하는 자료들을 추가로 넣어주자 -> id, pw 받아오자
        fun postRequestLogin(context: Context, id:String, pw:String, handler : jsonResponceHandler?){

            // 이 안드로이드 앱이 클라이언트로 동작하도록 도와주는 클래스 => 객체화
            val client = OkHttpClient()

            //어떤 기능을 수행하러 가는지 주소 완성 =>로그인 : http://15.165.177.142//user
            val urlString = "${BASE_URL}/user"
            //서버에 들고갈 데이터 첨부 intext putExtra와 비슷 =>POST FormData에 담자
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build() // 마무리

            //request 정보를 완성해주자. => 화면으로 따짐ㄴ Intent 객체 만드는행위
            val request = Request.Builder()
                .url(urlString) // 어디로
                .post(formData) // 들고갈 짐
                // API 가 헤더를 요구하면 여기서 정의한다.
                .build() // 마무리

            // stratActivity 처럼 요청을 날리는 코드
            // 요청을 날리면 서버의 응답처리도 같이 코딩
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    // 서버에 연결자체를 실패 했을때
                }

                override fun onResponse(call: Call, response: Response) {
                    // 서버에서 응답을 잘 받아왔을경우
                    //응답중에서 body를  String 로 저장
                    val bodyString = response.body!!.string()
                    //저장한 String을 JSONObject 형식으로 가공
                    val json = JSONObject(bodyString)
                    // 화면에 만들어낸  json변수를 전달
                    handler?.onResponce(json)
                }

            })
        }
    }

    // 서버통신의 응답내용을 화면으로 전달해주기 위한 인터페이스
    interface jsonResponceHandler {
        fun onResponce(json: JSONObject)
    }


}