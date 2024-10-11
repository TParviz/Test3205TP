package tj.test3205tj.domain.repository

import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity

interface DbRepository {

    suspend fun saveRepositoryDownload(repositoryDownloadEntity: RepositoryDownloadEntity)

    suspend fun getDownloadedRepositories(): List<RepositoryDownloadEntity>
}