package com.sun.mvvm.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sun.mvvm.data.repository.EmployeeRepository
import com.sun.mvvm.data.repository.EmployeeRepositoryImpl
import com.sun.mvvm.data.source.local.database.DatabaseConstants
import com.sun.mvvm.data.source.remote.EmployeeRemoteDataSource
import com.sun.mvvm.data.source.remote.api.EmployeeDataService
import com.sun.mvvm.data.repository.UserRepository
import com.sun.mvvm.data.repository.UserRepositoryImpl
import com.sun.mvvm.data.source.EmployeeDataSource
import com.sun.mvvm.data.source.UserDataSource
import com.sun.mvvm.data.source.local.UserLocalDataSource
import com.sun.mvvm.data.source.local.database.AppDataBase
import com.sun.mvvm.data.source.local.preferences.PreferencesHelper
import com.sun.mvvm.data.source.local.preferences.PreferencesHelperImpl
import com.sun.mvvm.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {

    //DataSource modules
    single<UserDataSource.Local> { UserLocalDataSource(get(), get()) }
    single<EmployeeDataSource.Remote> { EmployeeRemoteDataSource(get()) }
    //Repository modules
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<EmployeeRepository> {
        EmployeeRepositoryImpl(get())
    }
}

//database module
val databaseModule = module{

    //Room database modules
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
    //Room Dao modules
    single { get<AppDataBase>().userDao() }
    // Pref
    single<PreferencesHelper> { PreferencesHelperImpl(androidApplication()) }
}

// Api module
val apiModule = module {

    fun provideUseApi(retrofit: Retrofit): EmployeeDataService {
        return retrofit.create(EmployeeDataService::class.java)
    }

    single { provideUseApi(get()) }
}

// Retrofit Module
val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}
