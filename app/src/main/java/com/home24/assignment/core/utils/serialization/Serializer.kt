package com.home24.assignment.core.utils.serialization

import kotlinx.serialization.*
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody

internal sealed class Serializer {
    abstract fun <T> fromResponseBody(loader: DeserializationStrategy<T>, body: ResponseBody): T
    abstract fun <T> toRequestBody(
        contentType: MediaType,
        saver: SerializationStrategy<T>,
        value: T
    ): RequestBody

    @ExperimentalSerializationApi
    class FromString(private val format: StringFormat) : Serializer() {
        @ExperimentalSerializationApi
        override fun <T> fromResponseBody(
            loader: DeserializationStrategy<T>,
            body: ResponseBody
        ): T {
            val string = body.string()
            return format.decodeFromString(loader, string)
        }

        @ExperimentalSerializationApi
        override fun <T> toRequestBody(
            contentType: MediaType,
            saver: SerializationStrategy<T>,
            value: T
        ): RequestBody {
            val string = format.encodeToString(saver, value)
            return string.toRequestBody(contentType)
        }
    }

    @ExperimentalSerializationApi
    class FromBytes(private val format: BinaryFormat) : Serializer() {
        @ExperimentalSerializationApi
        override fun <T> fromResponseBody(
            loader: DeserializationStrategy<T>,
            body: ResponseBody
        ): T {
            val bytes = body.bytes()
            return format.decodeFromByteArray(loader, bytes)
        }

        override fun <T> toRequestBody(
            contentType: MediaType,
            saver: SerializationStrategy<T>,
            value: T
        ): RequestBody {
            val bytes = format.encodeToByteArray(saver, value)
            return bytes.toRequestBody(contentType, 0, bytes.size)
        }
    }
}