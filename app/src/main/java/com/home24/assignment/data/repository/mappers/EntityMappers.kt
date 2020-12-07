package com.home24.assignment.data.repository.mappers

import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.article.ArticleListResponse

/**
 * In [ArticleListResponse.Embedded.Article] -> Out: [ArticleEntity]
 */

object EntityMappers {

    fun toDto(entity: ArticleListResponse.Embedded.Article): ArticleEntity {

        when {
            entity.media.isNullOrEmpty() -> {
                return ArticleEntity(
                    id = entity.sku,
                    title = entity.title,
                    price = entity.price.amount,
                    imageUri = "",
                    isLiked = false,
                )
            }
            else -> {
                return ArticleEntity(
                    id = entity.sku,
                    title = entity.title,
                    price = entity.price.amount,
                    imageUri = entity.media[0].uri,
                    isLiked = false,
                )
            }
        }


    }


}