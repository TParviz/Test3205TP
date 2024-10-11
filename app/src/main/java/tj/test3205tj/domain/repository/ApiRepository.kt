package tj.test3205tj.domain.repository

import tj.test3205tj.domain.model.RepositoryInfo
import okhttp3.ResponseBody

interface ApiRepository {
    suspend fun getRepositories(query: String): List<RepositoryInfo>

    suspend fun downloadRepository(ownerName: String, repository: String): ResponseBody
}