package id.surya.home.master

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.surya.home.databinding.CategoryItemBinding
import id.surya.home.databinding.TransactionItemBinding
import id.surya.l_extras.data.model.Category
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.data.model.ProductPromo

class CategoryAdapter(private val data: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(data[pos])
    }

    class ItemHolder(private val categoryItemBinding: CategoryItemBinding) : RecyclerView.ViewHolder(categoryItemBinding.root) {

        fun bindItem(category:  Category) {
            categoryItemBinding.apply {
                this.category = category
            }

        }

    }

}