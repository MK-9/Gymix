package com.gymix.domain.util

sealed class OrderType {
    object ascending : OrderType()
    object descending : OrderType()
}
