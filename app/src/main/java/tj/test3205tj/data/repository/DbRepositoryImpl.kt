package tj.test3205tj.data.repository

import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadDAO
import tj.test3205tj.domain.repository.DbRepository
import kotlinx.coroutines.flow.flow
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val dao: RepositoryDownloadDAO) :
    DbRepository {

    override suspend fun saveRepositoryDownload(repositoryDownloadEntity: RepositoryDownloadEntity) {
        dao.addRepository(repositoryDownloadEntity)
    }

    override suspend fun getDownloadedRepositories(): List<RepositoryDownloadEntity> {
        return dao.getDownloadedRepositories()
    }
}