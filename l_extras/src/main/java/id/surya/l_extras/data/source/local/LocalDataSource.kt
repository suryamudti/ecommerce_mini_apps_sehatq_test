package id.surya.l_extras.data.source.local

import android.content.Context
import id.surya.l_extras.data.model.*
import id.surya.l_extras.data.source.AppDataSource
import id.surya.l_extras.data.source.local.dao.EcommerceDao
import id.surya.l_extras.data.source.local.dao.MovieShopDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val context: Context) : AppDataSource {

    override suspend fun getAllProducts(): Data? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllLocalProducts(): List<ProductPromo>? {
        return withContext(Dispatchers.IO) {
            ecommerceDao.getAllProducts()
        }
    }

    override suspend fun insertProduct(productPromo: ProductPromo) {
        withContext(Dispatchers.IO) {
            ecommerceDao.insertProducts(productPromo)
        }
    }

    private val movieShopDao: MovieShopDao by lazy {
        DataDBSource.getInstance(context).movieShopDao()
    }

    private val ecommerceDao: EcommerceDao by lazy {
        DataDBSource.getInstance(context).ecommerceDao()
    }

    override suspend fun searchMovie(query: String): List<Result>? {
        return emptyList()
    }

    override suspend fun getAllMovies(): List<Result>? {
        return emptyList()
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        withContext(Dispatchers.IO) {
            movieShopDao.insertMovieFilter(movieFilter)
        }
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return withContext(Dispatchers.IO) {
            movieShopDao.getAllMoviesFilter()
        }
    }

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        throw Exception("You can't get entertainment news list from local database")
    }

}