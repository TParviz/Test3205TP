package tj.test3205tj.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.test3205tj.data.repository.ApiRepositoryImpl
import tj.test3205tj.domain.repository.DbRepository
import tj.test3205tj.domain.usecase.DownloadRepositoryUseCase
import tj.test3205tj.domain.usecase.DownloadRepositoryUseCaseImpl
import tj.test3205tj.domain.usecase.GetDownloadRepositoriesUseCase
import tj.test3205tj.domain.usecase.GetDownloadRepositoriesUseCaseImpl
import tj.test3205tj.domain.usecase.GetRepositoriesUseCase
import tj.test3205tj.domain.usecase.GetRepositoriesUseCaseImpl
import tj.test3205tj.domain.usecase.SaveRepositoryDownloadUseCase
import tj.test3205tj.domain.usecase.SaveRepositoryDownloadUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetRepositoriesUseCase(
        repository: ApiRepositoryImpl
    ): GetRepositoriesUseCase = GetRepositoriesUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideDownloadRepositoryUseCase(
        repository: ApiRepositoryImpl
    ): DownloadRepositoryUseCase = DownloadRepositoryUseCaseImpl(repository)


    @Singleton
    @Provides
    fun provideSaveRepositoryDownloadUseCase(
        repository: DbRepository
    ): SaveRepositoryDownloadUseCase = SaveRepositoryDownloadUseCaseImpl(repository)


    @Singleton
    @Provides
    fun provideGetAllItemsUseCase(
        repository: DbRepository
    ): GetDownloadRepositoriesUseCase = GetDownloadRepositoriesUseCaseImpl(repository)
}