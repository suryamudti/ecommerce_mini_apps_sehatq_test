package id.surya.ecommerce

object AppNavigator {

    // app package
    const val BASE_PACKAGE = "id.surya."

    // feature package
    const val SPLASH_PATH = "ecommerce.SplashActivity"
    const val AUTH_PATH = "auth.AuthActivity"
    const val HOME_PATH = "home.HomeActivity"
    const val PRODUCT_DETAIL_PATH = "product_detail.ProductDetailActivity"
    const val PRODUCT_SEARCH_PATH = "product_search.ProductSearchActivity"

    fun getAuthRoute() = BASE_PACKAGE + AUTH_PATH
    fun getHomeRoute() = BASE_PACKAGE + HOME_PATH
    fun getProductDetailRoute() = BASE_PACKAGE + PRODUCT_DETAIL_PATH
    fun getSplashRoute() = BASE_PACKAGE + SPLASH_PATH
    fun getProductSearchRoute() = BASE_PACKAGE + PRODUCT_SEARCH_PATH

}