package com.innosync.data.util

internal object Env {
    object JobOpening {
        private const val hackathon = "/hackathon"
        const val hackathonGet = "${hackathon}/get"
        const val hackathonInsert = hackathon
        const val hackathonGetOne = hackathon
        const val hackathonSupport = "${hackathon}s/join"

        private const val eat = "/food"
        const val eatGet = "${eat}/get"
        const val eatInsert = eat
        const val eatGetOne = eat

        private const val exercise = "/exercise"
        const val exerciseGet = "${exercise}/get"
        const val exerciseInsert = exercise
        const val exerciseGetOne = exercise

    }

    object JobSearch {
        const val jobSearch = "/access"
        const val get = "${jobSearch}/get"
        const val getStack = "${jobSearch}/stack"
        const val insert = jobSearch
    }

    object User {
        private const val user = "/api/v1/users"
        const val get = "${user}/user"
        const val getThese = "${user}/user/all"
        const val notification = "/api/v1/notification"



    }

    object MyBox {
        const val get = "/hackathons/join"
    }

    object MyBox {
        const val get = "/hackathons/join"
    }

    object Alarm {
        private const val notification = "/api/v1/notification"
        const val get = "${notification}/get"
    }
    object Applicant{
        private const val myPost = "/hackathon/"
        const val myPostClick = "$myPost"

        const val applicants = "/hackathons/join"
    }

}
