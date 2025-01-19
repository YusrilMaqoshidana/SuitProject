package id.usereal.suitproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.usereal.suitproject.BuildConfig
import id.usereal.suitproject.data.local.remote_mediator.RemoteKeysDao
import id.usereal.suitproject.data.local.room.UserRoomDatabase
import id.usereal.suitproject.data.local.room.UsersDao
import id.usereal.suitproject.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injection {
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideMainRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): UserRoomDatabase {
        return Room.databaseBuilder(
            context,
            UserRoomDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUsersDao(database: UserRoomDatabase): UsersDao {
        return database.usersDao()
    }

    @Provides
    fun provideRemoteKeysDao(database: UserRoomDatabase): RemoteKeysDao {
        return database.remoteKeysDao()
    }
}