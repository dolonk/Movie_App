package com.example.movie_app.Service.Hilt


import com.example.movie_app.Service.Retrofit.Constants
import com.example.movie_app.Service.Retrofit.MovieInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun providesRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)
    }
}