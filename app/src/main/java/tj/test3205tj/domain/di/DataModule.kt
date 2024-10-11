package tj.test3205tj.domain.di

import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadDAO
import tj.test3205tj.data.repository.ApiRepositoryImpl
import tj.test3205tj.data.repository.DbRepositoryImpl
import tj.test3205tj.domain.repository.ApiRepository
import tj.test3205tj.domain.repository.DbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.test3205tj.data.api.GithubApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideGitHubRepository(api: GithubApi): ApiRepository {
        return ApiRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideDaoRepository(dao: RepositoryDownloadDAO): DbRepository {
        return DbRepositoryImpl(dao)
    }
}