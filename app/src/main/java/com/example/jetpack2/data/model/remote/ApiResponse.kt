package com.example.jetpack2.data.model.remote

import com.example.jetpack2.utils.StatusResponse

class ApiResponse<T>(val statusResponse: StatusResponse, val body: T?, val message: String?) {

    companion object {
        fun <T> success(body: T?): ApiResponse<T> {
            return ApiResponse(
                StatusResponse.SUCCESS,
                body,
                null
            )
        }

        fun <T> error(msg: String, body: T?): ApiResponse<T> {
            return ApiResponse(
                StatusResponse.ERROR,
                body,
                msg
            )
        }
    }
}