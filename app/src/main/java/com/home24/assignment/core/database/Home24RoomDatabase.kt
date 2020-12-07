package com.home24.assignment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.home24.assignment.data.datasource.local.dao.ArticleDao
import com.home24.assignment.data.datasource.local.entities.ArticleEntity

/**
 * Local [RoomDatabase]
 * Only 1 database instance is used in Application
 */

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Home24RoomDatabase : RoomDatabase() {

    companion object {
        const val HOME24_TABLE = "home24"
    }

    abstract fun getArticleDao(): ArticleDao

}