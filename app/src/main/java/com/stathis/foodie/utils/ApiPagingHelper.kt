package com.stathis.foodie.utils

class ApiPagingHelper {

    var noOfApiCalls = 0
    var from = 0
    var to = 10

    fun incrementCounters() {
        noOfApiCalls += 1

        when (noOfApiCalls) {
            1 -> {
                from = 0
                to = 10
            }
            else -> {
                from = (to + 1)
                to = from + 10
            }
        }
    }

    fun clearCounters() {
        noOfApiCalls = 0
        from = 0
        to = 0
    }
}