package com.innosync.hook.feature.mybox

import com.innosync.domain.model.HackathonModel

internal fun HackathonModel.toMyBoxRvData() =
    MyBoxRvData(
        content = content,
        status = status,
        date = regDate
    )

internal fun List<HackathonModel>.toMyBoxRvDatas() =
    this.map {
        it.toMyBoxRvData()
    }