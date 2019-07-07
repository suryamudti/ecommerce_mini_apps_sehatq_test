package id.surya.l_extras.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "tb_product")
data class ProductPromo(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "loved")
    val loved: Int,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "title")
    val title: String
)