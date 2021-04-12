package com.stathis.foodie.utils

class ApiPagingHelper {

    var noOfApiCalls = 0
    var oldCounter = 0
    var newCounter = 10

    fun incrementCounters() {
        noOfApiCalls += 1

        when (noOfApiCalls) {
            1 -> {
                oldCounter = 0
                newCounter = 10
            }
            else -> {
                oldCounter = (newCounter + 1)
                newCounter = oldCounter + 10
            }
        }
    }

    fun clearCounters() {
        noOfApiCalls = 0
        oldCounter = 0
        newCounter = 0
    }
}