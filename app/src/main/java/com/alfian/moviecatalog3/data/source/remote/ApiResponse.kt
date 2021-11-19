package com.alfian.moviecatalog3.data.source.remote

import com.alfian.moviecatalog3.data.source.remote.response.StatusResponse
import com.alfian.moviecatalog3.data.source.remote.response.StatusResponse.ERROR
import com.alfian.moviecatalog3.data.source.remote.response.StatusResponse.SUCCESS

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> error(msg: String): ApiResponse<T> = ApiResponse(ERROR, null, msg)
    }
}