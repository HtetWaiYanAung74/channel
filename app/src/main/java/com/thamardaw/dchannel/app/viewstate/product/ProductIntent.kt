package com.thamardaw.dchannel.app.viewstate.product

sealed class ProductIntent {
    object LoadProductIntent : ProductIntent()
}