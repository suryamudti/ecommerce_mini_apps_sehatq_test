package id.surya.home.master

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.surya.home.databinding.MasterCategoryHeaderBinding
import id.surya.home.databinding.MasterItemBinding
import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.data.model.ProductPromo

class MasterAdapter(private val data: List<ProductPromo>,
                    private val masterItemCallback: MasterItemCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_TYPE) {
            HeaderHolder(MasterCategoryHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            BodyHolder(MasterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return BODY_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as BodyHolder).bindItem(data[pos], masterItemCallback)
    }

    companion object {
        const val HEADER_TYPE = 0
        const val BODY_TYPE = 1
    }

    class HeaderHolder(private val masterCategoryHeaderBinding: MasterCategoryHeaderBinding) :
        RecyclerView.ViewHolder(masterCategoryHeaderBinding.root) {

        fun bindItem(masterItemCallback: MasterItemCallback) {
            masterCategoryHeaderBinding.apply {
                this.masterItemCallback = masterItemCallback
                executePendingBindings()
            }
        }
    }

    class BodyHolder(private val masterItemBinding: MasterItemBinding) :
        RecyclerView.ViewHolder(masterItemBinding.root) {

        fun bindItem(productPromo:  ProductPromo, masterItemCallback: MasterItemCallback) {
            masterItemBinding.apply {
                this.productPromo = productPromo

                if (productPromo.loved==1)
                    this.love = true
                else
                    this.love = false


                this.masterItemCallback = masterItemCallback
                executePendingBindings()
            }
        }

    }

}
