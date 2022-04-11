package com.eratart.baristashandbook.data.mapper

interface IMapper<F, T> {

    fun mapFrom(item: F): T

    fun mapTo(item: T): F

}