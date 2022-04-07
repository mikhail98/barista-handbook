package com.eratart.baristashandbook.core.ext

fun <T> MutableList<T>.replaceAllWith(collection: Collection<T>) {
    this.clear()
    this.addAll(collection)
}