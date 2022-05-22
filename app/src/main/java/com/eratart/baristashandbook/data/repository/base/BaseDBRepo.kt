package com.eratart.baristashandbook.data.repository.base

import com.eratart.baristashandbook.data.mapper.IFirebaseDBMapper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

abstract class BaseDBRepo(private val database: FirebaseDatabase) : BaseRepo() {

    companion object {
        const val DATABASE =
            "https://barista-s-handbook-default-rtdb.europe-west1.firebasedatabase.app/"
    }

    abstract val databasePostfix: String

    protected fun <T> getDataFlowFromDatabase(mapper: IFirebaseDBMapper<T>): Flow<List<T>> {
        return callbackFlow {
            val reference = database.getReferenceFromUrl(DATABASE.plus(databasePostfix))
            val postListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    throw error.toException()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    trySendBlocking(mapper.mapFromDB(dataSnapshot))
                }
            }
            reference.addValueEventListener(postListener)

            awaitClose {
                reference.removeEventListener(postListener)
            }
        }
    }
}