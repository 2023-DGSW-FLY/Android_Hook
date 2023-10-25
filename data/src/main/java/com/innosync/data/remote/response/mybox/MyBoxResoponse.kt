package com.innosync.data.remote.response.mybox

import com.google.gson.annotations.SerializedName
import com.google.type.DateTime
import java.time.LocalDateTime

data class MyBoxResoponse (
    @field:SerializedName("regDate")
    val regDate : LocalDateTime,
    @field:SerializedName("modDate")
    val modDate: LocalDateTime,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("writer")
    val writer: String,
    @field:SerializedName("userName")
    val userName: String,
)


//"regDate": "2023-10-06T00:27:36.669499",
//"modDate": "2023-10-06T00:27:36.669499",
//"id": 1,
//"title": "마소 해커톤 나갈사람",
//"content": "저는 기획자이며 양예성이 팀에 있어요 우승 지망자들 구합니다",

//"stack": "디자인,안드로이드",
//"url": "ptowin.com",
//"status": "matching",
//"writer": "yeseon1231g",
//"userName": "yeseong"