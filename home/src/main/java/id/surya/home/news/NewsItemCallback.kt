package id.surya.home.news

import id.surya.l_extras.data.model.Article

interface NewsItemCallback {
    fun onItemNewsSelected(article: Article)
}