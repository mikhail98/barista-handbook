package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.Item

interface IItemsRepo {
    fun getItems(): List<Item>
}