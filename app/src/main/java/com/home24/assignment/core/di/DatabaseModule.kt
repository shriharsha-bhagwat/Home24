package com.home24.assignment.core.di

import android.app.Application
import androidx.room.Room
import com.home24.assignment.core.database.Home24RoomDatabase
import com.home24.assignment.data.datasource.local.dao.ArticleDao
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import com.ironz.binaryprefs.Preferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * [DatabaseModule] provides RoomDatabase, DAO instances, SharedPrefs
 */

private const val DATABASE_NAME = "home24_database"
private const val PREFERENCES_NAME = "home24_sharedpref"

val DatabaseModule = module {

    single { provideDatabase(androidApplication()) }
    single { provideArticleDao(db = get()) }
    single { providePreferences(androidApplication()) }

}

private fun providePreferences(app: Application): Preferences {
    return BinaryPreferencesBuilder(app).name(PREFERENCES_NAME).build()
}

private fun provideDatabase(app: Application): Home24RoomDatabase {
    return Room
        .databaseBuilder(app, Home24RoomDatabase::class.java, DATABASE_NAME)
        .build()
}

private fun provideArticleDao(db: Home24RoomDatabase): ArticleDao = db.getArticleDao()