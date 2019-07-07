package id.surya.home.transaction

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.surya.home.databinding.TransactionItemBinding
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.data.model.ProductPromo

class TransactionAdapter(private val data: List<ProductPromo>) : RecyclerView.Adapter<TransactionAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(data[pos])
    }

    class ItemHolder(private val transactionItemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(transactionItemBinding.root) {

        fun bindItem(productPromo:  ProductPromo) {
            transactionItemBinding.apply {
                this.product = productPromo
            }

        }

    }

}