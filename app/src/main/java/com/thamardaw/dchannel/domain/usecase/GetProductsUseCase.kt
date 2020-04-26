package com.thamardaw.dchannel.domain.usecase

import com.thamardaw.dchannel.data.repository.productRepo
import com.thamardaw.dchannel.domain.repository.ProductsRepository

class GetProductsUseCase(
    private val productsRepository: ProductsRepository = productRepo
) {
    suspend operator fun invoke() = productsRepository.getProductsWithCategory()
}