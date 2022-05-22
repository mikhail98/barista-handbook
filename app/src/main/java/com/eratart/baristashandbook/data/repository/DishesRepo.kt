package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.data.mapper.repo.DishesDBMapper
import com.eratart.baristashandbook.data.repository.base.BaseDBRepo
import com.eratart.baristashandbook.domain.repository.IDishesRepo
import com.google.firebase.database.FirebaseDatabase

class DishesRepo(database: FirebaseDatabase) : BaseDBRepo(database), IDishesRepo {

    override val databasePostfix = "1EzzVuFtO9HsD1z7pencFROPtnrdgMzperQ7Pb02jfTw/ru"

    override fun getDishes() = getDataFlowFromDatabase(DishesDBMapper())
}