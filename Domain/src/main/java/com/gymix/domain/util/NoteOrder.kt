package com.gymix.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType = orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType = orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType = orderType)

    fun copy(orderType: OrderType) {
        when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
