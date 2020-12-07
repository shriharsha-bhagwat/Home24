package com.home24.assignment.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.home24.assignment.core.database.Home24RoomDatabase

@Entity(tableName = Home24RoomDatabase.HOME24_TABLE)
data class ArticleEntity(

	@PrimaryKey
	val id: String,
	val title: String,
	val price: String,
	val imageUri: String?,
	var isLiked: Boolean = false,
	var isDisLiked: Boolean = false
)