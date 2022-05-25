package com.eratart.baristashandbook.data.mapper

import com.google.firebase.database.DataSnapshot

interface IFirebaseDBMapper<T> {

    fun mapFromDB(snapshot: DataSnapshot): List<T>

}