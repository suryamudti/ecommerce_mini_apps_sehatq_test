package id.surya.home.transaction

import android.arch.lifecycle.Observer
import id.surya.home.R
import id.surya.home.databinding.TransactionFragmentBinding
import id.surya.l_extras.base.BaseFragment
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.ext.putArgs
import id.surya.l_extras.ext.verticalListStyle
import id.surya.ecommerce.MainApp
import id.surya.l_extras.data.model.ProductPromo
import kotlinx.android.synthetic.main.transaction_fragment.*

class TransactionFragment : BaseFragment<TransactionFragmentBinding, TransactionVM>() {

    override fun bindLayoutRes() = R.layout.transaction_fragment

    override fun onSetViewModel(): TransactionVM {
        return TransactionVM(MainApp.instance)
    }

    override fun onLoadObserver(baseViewModel: TransactionVM) {
        baseViewModel.apply {
            productListListEvent.observe(this@TransactionFragment, Observer { data ->
                data?.let {
                    setupTransactionsList(it)
                }
            })
        }
    }

    override fun onStartWork() {

    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                transactionVM = it
            }
        }
    }

    private fun setupTransactionsList(data: List<ProductPromo>) {
        rec_transaction.apply {
            verticalListStyle()
            adapter = TransactionAdapter(data)
        }
    }

    companion object {
        fun newInstance() = TransactionFragment().putArgs { }
    }
}