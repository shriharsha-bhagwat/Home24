package com.home24.assignment.data.repository

import com.home24.assignment.domain.article.ArticleListResponse
import com.home24.assignment.readJson
import kotlinx.serialization.json.Json

/**
 * ArticleData from JSON file
 */

object ArticleData {

    private val responseList = readJson("Home24")

    val DTO_RESPONSE: ArticleListResponse =
        Json { ignoreUnknownKeys = true }
            .decodeFromString(ArticleListResponse.serializer(), responseList)
}