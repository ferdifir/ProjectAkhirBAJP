package com.example.jetpack2.utils

class Resource<T>(
    val status: Status,
    val data: T?,
    private val message: String?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other == null || javaClass != other.javaClass)
            return false

        val resource = other as Resource<*>?

        if (status !== resource!!.status)
            return false

        if (if (message != null) message != resource!!.message else resource!!.message != null)
            return false

        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}