package com.gymix.domain.useCase

import com.gymix.domain.entity.Note
import com.gymix.domain.util.NoteOrder
import com.gymix.domain.util.OrderType
import io.reactivex.rxjava3.core.Single

interface GetNotesUseCase {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.ascending)): Single<List<Note>>
}