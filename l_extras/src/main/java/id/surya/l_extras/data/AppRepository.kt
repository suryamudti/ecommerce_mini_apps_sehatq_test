package id.surya.l_extras.data


import id.surya.l_extras.data.model.*
import id.surya.l_extras.data.source.AppDataSource
import id.surya.l_extras.data.source.local.LocalDataSource
import id.surya.l_extras.data.source.remote.RemoteDataSource

class AppRepository(
    private val remoteDataSource: AppDataSource,
    private val localDataSource: AppDataSource
) : AppDataSource {


    override suspend fun getAllLocalProducts(): List<ProductPromo>? {
        return localDataSource.getAllLocalProducts()
    }

    override suspend fun insertProduct(productPromo: ProductPromo) {
        return localDataSource.insertProduct(productPromo)
    }

    override suspend fun searchMovie(query: String): List<Result>? {
        return remoteDataSource.searchMovie(query)
    }

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        return remoteDataSource.getAllEntertainmentNews()
    }

    override suspend fun getAllMovies(): List<Result>? {
        return remoteDataSource.getAllMovies()
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        localDataSource.insertMovieFilter(movieFilter)
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return localDataSource.getAllMoviesFilter()
    }

    override suspend fun getAllProducts(): Data? {
        return remoteDataSource.getAllProducts()
    }

    companion object {
        var mRepository: AppRepository? = null

        @JvmStatic
        fun getInstance(dataRemoteSource: RemoteDataSource, dataLocalSource: LocalDataSource): AppRepository {
            if (mRepository == null) {
                mRepository = AppRepository(remoteDataSource = dataRemoteSource, localDataSource = dataLocalSource)
            }
            return mRepository!!
        }
    }
}