package com.innosync.data.repository

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.innosync.data.remote.firebase.mapper.toModel
import com.innosync.data.remote.firebase.request.ChatRequest
import com.innosync.data.remote.firebase.request.RoomRequest
import com.innosync.data.remote.firebase.response.ChatResponse
import com.innosync.data.remote.firebase.response.RoomResponse
import com.innosync.data.util.FIreStoreEnv
import com.innosync.domain.exception.FirebaseInsertException
import com.innosync.domain.model.ChatData
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
                val rooms = snapshot?.documents!!.map { it.toObject(RoomResponse::class.java)!!.toModel().copy(chatRoomUid = it.id) }
                action.invoke(rooms)
            }
            .addOnFailureListener {
                action.invoke(emptyList())
            }
    }

    override suspend fun insertRoom(userId: String, targetId: String) {
        val value = listOf(false, true)
        database.collection("room")
            .whereIn("users.$userId", value)
            .whereIn("users.$targetId", value)
            .get()
            .addOnSuccessListener { snapshot ->
                Log.d(TAG, "insertRoom: ${snapshot.documents}")
                if (snapshot.documents.isNotEmpty()) { return@addOnSuccessListener }
                database.collection(FIreStoreEnv.ROOM)
                    .add(
                        RoomRequest(
                            users = mapOf(
                                userId to false,
                                targetId to false
                            )
                        )
                    ).addOnFailureListener {
                        throw FirebaseInsertException(it.message.toString())
                    }
            }.addOnFailureListener {
                throw FirebaseInsertException(it.message.toString())
            }

    }

    override suspend fun eventChatLister(chatUid: String, action: (List<ChatData>) -> Unit): Unit {
        database.collection("room/${chatUid}/messages")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }
                var chats = snapshot?.documents?.map { it.toObject(ChatResponse::class.java)?.toModel()!! }!!
                chats = chats.sortedBy { it.timestamp }
                action(chats)
            }
    }

    override suspend fun sendMessage(userId: String, chatUid: String, content: String) {
        Log.d(TAG, "sendMessage: $content")
        database.collection("room/${chatUid}/messages")
            .add(
                ChatRequest(
                    author = userId,
                    content = content
                )
            ).addOnSuccessListener {
                Log.d(TAG, "initSendChat: $it")
            }.addOnFailureListener {
                Log.d(TAG, "initSendChat: $it")
            }.addOnCanceledListener {
                Log.d(TAG, "sendMessage: ")
            }
        Log.d(TAG, "testMess")
        database.collection("room")
            .document(chatUid)
            .get()
            .addOnSuccessListener {
                Log.d(TAG, "sendMessage: $userId")
                var data = it.toObject(RoomResponse::class.java)!!
                data = data.copy(
                    lastMessage = content,
                    timestamp = Timestamp.now()//Timestamp.now()
                )
                database.collection("room")
                    .document(chatUid)
                    .set(data)
                    .addOnSuccessListener {
                        Log.d(TAG, "initSendChat: $it.")
                    }
            }.addOnFailureListener {
                Log.d(TAG, "sendMessage: ${it.message}")
            }
    }

    companion object {
        const val TAG = "LOG"
    }
}