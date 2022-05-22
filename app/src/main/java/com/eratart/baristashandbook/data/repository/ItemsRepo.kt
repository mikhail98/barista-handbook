package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.data.mapper.repo.ItemsFirebaseDBMapper
import com.eratart.baristashandbook.data.repository.base.BaseDBRepo
import com.eratart.baristashandbook.domain.repository.IItemsRepo
import com.google.firebase.database.FirebaseDatabase

class ItemsRepo(database: FirebaseDatabase) : BaseDBRepo(database), IItemsRepo {

    override val databasePostfix = "1IHVdhogPA-SPO3Uf50s-L1m0Aiq5cF5Rhd3SY3KzOlk/ru"

    override fun getItems() = getDataFlowFromDatabase(ItemsFirebaseDBMapper())
}