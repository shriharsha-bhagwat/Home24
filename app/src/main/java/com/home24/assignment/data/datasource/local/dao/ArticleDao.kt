package com.home24.assignment.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.home24.assignment.core.database.Home24RoomDatabase
import com.home24.assignment.data.datasource.local.entities.ArticleEntity

/**
 * Dao is responsible for storing and retrieving Articles info from database
 */

@Dao
interface ArticleDao {

    @Transaction
    @Query("SELECT * FROM ${Home24RoomDatabase.HOME24_TABLE} ORDER By id")
    fun observeArticles(): LiveData<List<ArticleEntity>>

    @Query("SELECT * FROM ${Home24RoomDatabase.HOME24_TABLE}")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM ${Home24RoomDatabase.HOME24_TABLE} WHERE id = :id")
    suspend fun getArticlesById(id: String): ArticleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

    @Query("DELETE FROM ${Home24RoomDatabase.HOME24_TABLE}")
    suspend fun clearAll()

}