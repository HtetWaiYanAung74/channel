package com.thamardaw.dchannel.data.exception

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    data class GlobalError(val globalEvent: GlobalEvent) : DataException()

    object Network : DataException()

    object Conversion : DataException()

    class Api(message: String) : DataException(message)
}