package com.gymix.training

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticModule {

    @Binds
    abstract fun provideAnalyticService(analyticServiceImpl: AnalyticServiceImpl): AnalyticsService

}