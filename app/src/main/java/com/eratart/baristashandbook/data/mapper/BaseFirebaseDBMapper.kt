package com.eratart.baristashandbook.data.mapper

import com.google.firebase.database.DataSnapshot

abstract class BaseFirebaseDBMapper<T> : IFirebaseDBMapper<T> {

    protected fun HashMap<String, String>.getParam(param: String) = this[param]?.trim()

    override fun mapFromDB(snapshot: DataSnapshot): List<T> {
        val inputList = mutableListOf<HashMap<String, String>>()
        snapshot.children.forEach { data ->
            val map = data.value as HashMap<String, Any?>
            val newHashMap = HashMap<String, String>()
            map.keys.forEach {
                newHashMap[it] = map[it].toString()
            }
            inputList.add(newHashMap)

        }
        return mapFromData(inputList)
    }

    abstract fun mapFromData(inputList: List<HashMap<String, String>>): List<T>
}