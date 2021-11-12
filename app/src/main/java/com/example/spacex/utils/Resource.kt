package com.example.spacex.utils

import com.example.spacex.enum.ResponseStatus

//Generic class for handling Api response
data class Resource<out T>(val status: ResponseStatus, val data: T?, val message: String?, val code: Int? = null) {
    companion object {

        /**
         * Generic method for handling success response
         */
        fun <T> success(data: T): Resource<T> = Resource(status = ResponseStatus.SUCCESS, data = data, message = null)

        /**
         * Generic method for handling error response
         */
        fun <T> error(data: T?, message: String, code: Int?): Resource<T> =
            Resource(status = ResponseStatus.ERROR, data = data, message = message, code = code)

        /**
         * Generic method for manging state till the response receive from the server
         */
        fun <T> loading(data: T?): Resource<T> = Resource(status = ResponseStatus.LOADING, data = data, message = null)
    }
}