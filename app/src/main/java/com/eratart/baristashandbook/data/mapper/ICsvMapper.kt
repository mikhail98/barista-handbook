package com.eratart.baristashandbook.data.mapper

interface ICsvMapper<T> {

    fun mapFromCsvLine(inputList: List<Array<String>>): List<T>
}