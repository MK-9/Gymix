package com.gymix.domain.interactors

import com.gymix.domain.entity.Clip
import com.gymix.domain.interfaces.GymixRepositoryInterface
import com.gymix.domain.useCase.ClipListUseCases

class ClipListInteractor constructor(val repo: GymixRepositoryInterface) : ClipListUseCases {

    override fun getClipByPage(page: Int): List<Clip> {
        return repo.getClipData()
    }
}