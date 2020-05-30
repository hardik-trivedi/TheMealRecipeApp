package com.hardiktrivedi.theinternationaldhaba.exception

/**
 *  A exception which represent the state where no internet is available while application is running.
 */
class NoInternetAvailableException : Exception()

/**
 *  A exception which represent the state where no recipe is found while application is running.
 */
class NoRecipeOfTheDayFoundException : Exception()

/**
 *  A exception which represent the state where some generic error happened while application is running.
 */
class SomethingWentWrongException : Exception()
