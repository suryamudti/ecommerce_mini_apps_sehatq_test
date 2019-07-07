package id.surya.l_extras.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.surya.l_extras.data.model.ProductPromo

@Dao
interface EcommerceDao {

    @Query("SELECT * FROM tb_product")
    fun getAllProducts(): List<ProductPromo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(data: ProductPromo)
}