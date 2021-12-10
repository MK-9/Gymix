package com.gymix.training

import android.util.Log
import javax.inject.Inject

class AnalyticServiceImpl @Inject constructor() : AnalyticsService {

    override fun analyticsMethods() {
        Log.d("MrKing", "analyticsMethods")
    }
}