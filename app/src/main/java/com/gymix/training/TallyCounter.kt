package com.gymix.training

interface TallyCounter {
    fun reset()
    fun increment()
    fun getCount():Int
    fun setCount(count: Int)
    fun isActive():Boolean
}