package com.gymix.training

import javax.inject.Inject

class AnalyticsAdapter @Inject constructor(private val analyticsService: AnalyticsService) {

    fun printData() {
        analyticsService.analyticsMethods()
    }

}