package com.gymix.domain.useCase

import com.gymix.domain.entity.Clip

interface ClipListUseCases {
    fun getClipByPage(page: Int): List<Clip>
}