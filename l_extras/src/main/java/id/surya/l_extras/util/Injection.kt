package id.surya.l_extras.util

import android.app.Application
import id.surya.l_extras.data.AppRepository
import id.surya.l_extras.data.source.local.LocalDataSource
import id.surya.l_extras.data.source.remote.RemoteDataSource

object Injection {
    fun provideAppRepository(context: Application): AppRepository {
        return AppRepository.getInstance(
            RemoteDataSource(context),
            LocalDataSource(context.applicationContext)
        )
    }
}