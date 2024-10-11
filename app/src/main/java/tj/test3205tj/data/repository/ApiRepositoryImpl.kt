package tj.test3205tj.data.repository

import tj.test3205tj.domain.model.RepositoryInfo
import tj.test3205tj.domain.repository.ApiRepository
import okhttp3.ResponseBody
import tj.test3205tj.data.api.GithubApi
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api: GithubApi) : ApiRepository {

    override suspend fun getRepositories(query: String): List<RepositoryInfo> {
        return api.getSearchRepositories(query)
    }

    override suspend fun downloadRepository(ownerName: String, repository: String) : ResponseBody  {
        return api.downloadRepository(ownerName, repository)
    }
}