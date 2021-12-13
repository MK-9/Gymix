package com.gymix.domain.interfaces

import com.gymix.domain.entity.Clip

interface GymixRepositoryInterface {
    fun getClipData(): List<Clip>
}