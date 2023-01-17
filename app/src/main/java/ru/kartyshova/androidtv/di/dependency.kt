package ru.kartyshova.androidtv.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kartyshova.androidtv.data.ChannelsApi
import ru.kartyshova.androidtv.data.ChannelsRepository
import ru.kartyshova.androidtv.data.ChannelsRepositoryImpl
import ru.kartyshova.androidtv.data.LocalRepository
import ru.kartyshova.androidtv.db.AndroidTvDatabase
import ru.kartyshova.androidtv.presentation.ChannelsViewModel
import ru.kartyshova.androidtv.presentation.FavoriteViewModel
import ru.kartyshova.androidtv.usecase.GetChannelsUseCase


const val BASE_URL = "https://limehd.online/"

val dependency = module {
    single<ChannelsApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@single retrofit.create(ChannelsApi::class.java)
    }

    single<ChannelsRepository> { ChannelsRepositoryImpl(get(), get()) }
    single { LocalRepository(get()) }

    single { GetChannelsUseCase(get()) }

    viewModel{ ChannelsViewModel(get(),get()) }
    viewModel{ FavoriteViewModel(get()) }

    single {
        val db = Room.databaseBuilder(androidContext(), AndroidTvDatabase::class.java, "android-tv")
            .build()

        db.channelDao()
    }
}