package id.surya.l_extras.ext

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

fun RecyclerView.verticalListStyle() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}

fun RecyclerView.horizontalListStyle() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}


fun View.visible(

) {
    if (this.visibility == View.GONE) this.visibility = View.VISIBLE
}

fun View.gone(

) {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible(

) {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}