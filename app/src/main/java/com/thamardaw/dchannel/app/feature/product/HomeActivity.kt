package com.thamardaw.dchannel.app.feature.product

import androidx.activity.viewModels
import com.thamardaw.dchannel.R
import com.thamardaw.dchannel.app.base.MviActivity
import com.thamardaw.dchannel.app.viewstate.product.ProductEvent
import com.thamardaw.dchannel.app.viewstate.product.ProductsViewState

class HomeActivity : MviActivity<ProductViewModel, ProductsViewState, ProductEvent>() {

    private val productViewModel by viewModels<ProductViewModel>()


    override fun setUpLayout() {
        setContentView(R.layout.activity_home)
    }

    override fun getViewModel(): ProductViewModel = productViewModel

    override fun render(viewState: ProductsViewState) {
    }

    override fun renderEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.NetworkError -> {
                if (!offlineAlertDialog.isShowing) {
                    offlineAlertDialog.show()
                }
            }
        }
    }
}