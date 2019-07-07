package id.surya.home.master

import id.surya.l_extras.data.model.MovieFilter
import id.surya.l_extras.data.model.ProductPromo

interface MasterItemCallback {
    fun onCategoryMovieSelected(genre: String)
    fun onItemMovieSelected(movieFilter: MovieFilter)
    fun onItemProductSelected(productPromo: ProductPromo)
}