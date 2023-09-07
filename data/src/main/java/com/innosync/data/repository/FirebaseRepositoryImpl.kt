package com.innosync.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.innosync.data.remote.firebase.mapper.toModel
import com.innosync.data.remote.firebase.response.RoomInfo
import com.innosync.data.util.FIreStoreEnv
import com.innosync.domain.model.RoomData
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject


class FirebaseRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
): FirebaseRepository {


    override suspend fun getRoomList(userId: String, action: (List<RoomData>) -> Unit) {
        database.collection(FIreStoreEnv.ROOM)
            .whereIn("users.$userId", listOf(true, false))
            .get()
            .addOnSuccessListener { snapshot ->
                val rooms = snapshot?.documents!!.map { it.toObject(RoomInfo::class.java)!!.toModel() }
                action.invoke(rooms)
            }
            .addOnFailureListener {
                action.invoke(emptyList())
            }
    }

}