package tj.test3205tj.domain.di

import android.app.Application
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadDAO
import tj.test3205tj.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Application): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getDao(app: AppDatabase): RepositoryDownloadDAO {
        return app.repositoryDownloadDao()
    }
}