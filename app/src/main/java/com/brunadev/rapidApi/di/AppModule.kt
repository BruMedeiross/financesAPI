package com.brunadev.rapidApi.di

import com.brunadev.rapidApi.commom.ApiEndPoints
import com.brunadev.rapidApi.presenter.Details.DetailViewModel
import com.brunadev.rapidApi.presenter.Main.MainViewModel
import com.brunadev.rapidApi.repository.EventImpl
import com.brunadev.rapidApi.repository.Repository


import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel

object AppModule {

    val appModule = module {

        single { provideApi(get()) }
        single<Repository> { EventImpl() }

        viewModel { MainViewModel(get()) }
        viewModel { DetailViewModel() }

    }

    private fun provideApi(retrofit: Retrofit): ApiEndPoints =
        retrofit.create(ApiEndPoints::class.java)

}
