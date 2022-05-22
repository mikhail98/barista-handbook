package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.data.mapper.repo.ItemsCategoriesDBMapper
import com.eratart.baristashandbook.data.repository.base.BaseDBRepo
import com.eratart.baristashandbook.domain.repository.IItemCategoriesRepo
import com.google.firebase.database.FirebaseDatabase

class ItemCategoriesRepo(database: FirebaseDatabase) : BaseDBRepo(database), IItemCategoriesRepo {

    override val databasePostfix = "14dOX1cULPiBdRH_nS-TrSrYfoVvWj-MzYxKdsROMxoc/ru"

    override fun getItemCategories() = getDataFlowFromDatabase(ItemsCategoriesDBMapper())

}