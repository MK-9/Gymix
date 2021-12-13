package com.gymix.data.repositories

import com.gymix.data.database.DbRepository
import com.gymix.data.network.ApiRepository
import com.gymix.domain.entity.Clip
import com.gymix.domain.interfaces.GymixRepositoryInterface

class GymixRepository constructor(
    val apiRepository: ApiRepository,
    val dbRepository: DbRepository
) : GymixRepositoryInterface {

    override fun getClipData(): List<Clip> {

    }

}