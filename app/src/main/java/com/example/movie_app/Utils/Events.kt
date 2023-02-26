package com.example.movie_app.Utils

class Events<out T>(private val content: T) {

    var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content

        } else {
            null
        }
    }

    fun peekContent(): T = content
}
