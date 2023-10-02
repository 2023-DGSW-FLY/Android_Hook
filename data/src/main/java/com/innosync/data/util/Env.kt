package com.innosync.data.util

object Env {
    object JobOpening {
        private const val hackathon = "/hackathon"
        const val hackathonGet = "${hackathon}/get"

        private const val eat = "/food"
        const val eatGet = "${eat}/all"

        private const val exercise = "/exercise"
        const val exerciseGet = "${exercise}/all"
    }

    object JobSearch {
        private const val jobSearch = "/access"
        const val get = "${jobSearch}/get"
    }
}
