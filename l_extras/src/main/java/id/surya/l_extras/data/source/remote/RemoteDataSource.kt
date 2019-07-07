package id.surya.l_extras.data.source.remote

import android.app.Application
import android.util.Log
import id.surya.l_extras.data.model.*
import id.surya.l_extras.data.source.AppDataSource
import id.surya.l_extras.util.Results
import kotlinx.io.IOException
import retrofit2.Response

class RemoteDataSource(private val application: Application) : AppDataSource {

    override suspend fun getAllLocalProducts(): List<ProductPromo>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertProduct(productPromo: ProductPromo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val entertaimentNewsApiService: ApiService by lazy {
        ApiService.newBuilder(application, BASE_URL_NEWS)
    }

    private val movieApiService: ApiService by lazy {
        ApiService.newBuilder(application, BASE_URL_MOVIE)
    }

    private val productApiService: ApiService by lazy {
        ApiService.newBuilder(application, BASE_URL_PRODUCT)
    }

    override suspend fun searchMovie(query: String): List<Result>? {
        val searchMovieResponse = safeApiCall(
            call = {movieApiService.searchMoviesAsync(query).await()},
            errorMessage = "Error Search Movie"
        )

        return searchMovieResponse?.results
    }

    override suspend fun getAllMovies(): List<Result>? {
        val movieResponse = safeApiCall(
                call = { movieApiService.getMoviesAsync().await() },
                errorMessage = "Error Fetching Movies List"
        )

        return movieResponse?.results
    }

    override suspend fun getAllProducts(): Data? {
        val response = safeApiCall(
            call = { productApiService.getProductAsync().await()},
            errorMessage = "Error fetching product data"
        )
        return response?.get(0)?.data
    }

    override suspend fun insertMovieFilter(movieFilter: MovieFilter) {
        throw Exception("Can't insert data from remote source")
    }

    override suspend fun getAllMoviesFilter(): List<MovieFilter> {
        return emptyList()
    }

    override suspend fun getAllEntertainmentNews(): List<Article>? {
        val entertainmentNewsResponse = safeApiCall(
                call = { entertaimentNewsApiService.getEntertaimentNewsAsync().await() },
                errorMessage = "Error Fetching Entertainment News"
        )

        return entertainmentNewsResponse?.articles
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Results<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Results.Success ->
                data = result.data

            is Results.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Results<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Results.Success(response.body()!!)

        return Results.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }

    companion object {
        const val BASE_URL_MOVIE = "https://api.themoviedb.org/"
        const val BASE_URL_NEWS = "https://newsapi.org/"
        const val BASE_URL_PRODUCT = "https://private-4639ce-ecommerce56.apiary-mock.com/"
    }

}