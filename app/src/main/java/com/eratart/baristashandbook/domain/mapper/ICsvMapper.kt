package com.eratart.baristashandbook.domain.mapper

interface ICsvMapper<T> {

    fun mapFromCsvLine(inputList: List<Array<String>>): List<T>
}