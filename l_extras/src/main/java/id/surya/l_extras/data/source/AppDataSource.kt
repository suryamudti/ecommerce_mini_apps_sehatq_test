package id.surya.l_extras.data.source


import id.surya.l_extras.data.model.*

interface AppDataSource {

    suspend fun searchMovie(query: String): List<Result>?

    suspend fun getAllMovies(): List<Result>?

    suspend fun getAllProducts() : Data?

    suspend fun getAllEntertainmentNews(): List<Article>?

    suspend fun insertMovieFilter(movieFilter: MovieFilter)

    suspend fun insertProduct(productPromo: ProductPromo)

    suspend fun getAllMoviesFilter(): List<MovieFilter>

    suspend fun getAllLocalProducts() : List<ProductPromo>?

}