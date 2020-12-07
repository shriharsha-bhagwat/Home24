package com.home24.assignment.domain.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleListResponse(
    @SerialName("_embedded")
    val embedded: Embedded = Embedded()
) {
    @Serializable
    data class Embedded(
        @SerialName("articles")
        val articles: List<Article> = listOf()
    ) {
        @Serializable
        data class Article(
            @SerialName("availableForAR")
            val availableForAR: Boolean = false,
            @SerialName("brand")
            val brand: Brand = Brand(),
            @SerialName("description")
            val description: String = "",
            @SerialName("discount")
            val discount: Double = 0.0,
            @SerialName("_links")
            val links: Links = Links(),
            @SerialName("media")
            val media: List<Media> = listOf(),
            @SerialName("_metadata")
            val metadata: Metadata = Metadata(),
            @SerialName("price")
            val price: Price = Price(),
            @SerialName("reviews")
            val reviews: Reviews = Reviews(),
            @SerialName("showrooms")
            val showrooms: List<Showroom> = listOf(),
            @SerialName("sku")
            val sku: String = "",
            @SerialName("title")
            val title: String = ""
        ) {
            @Serializable
            data class Brand(
                @SerialName("description")
                val description: String = "",
                @SerialName("id")
                val id: String = ""
            )

            @Serializable
            data class Links(
                @SerialName("self")
                val self: Self = Self()
            ) {
                @Serializable
                data class Self(
                    @SerialName("href")
                    val href: String = ""
                )
            }

            @Serializable
            data class Media(
                @SerialName("mimeType")
                val mimeType: String = "",
                @SerialName("priority")
                val priority: Int = 0,
                @SerialName("uri")
                val uri: String = ""
            )

            @Serializable
            data class Metadata(
                @SerialName("type")
                val type: String = ""
            )

            @Serializable
            data class Price(
                @SerialName("amount")
                val amount: String = "",
                @SerialName("currency")
                val currency: String = "",
                @SerialName("recommendedRetailPrice")
                val recommendedRetailPrice: Boolean = false
            )

            @Serializable
            data class Reviews(
                @SerialName("count")
                val count: Int = 0,
                @SerialName("rating")
                val rating: Int = 0
            )

            @Serializable
            data class Showroom(
                @SerialName("catalogKey")
                val catalogKey: String = "",
                @SerialName("name")
                val name: String = "",
                @SerialName("url")
                val url: String = ""
            )
        }
    }
}