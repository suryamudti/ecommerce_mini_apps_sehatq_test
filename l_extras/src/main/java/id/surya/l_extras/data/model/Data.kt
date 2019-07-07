package id.surya.l_extras.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("productPromo")
    val productPromo: List<ProductPromo>
)