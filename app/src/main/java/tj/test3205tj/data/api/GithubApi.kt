package tj.test3205tj.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tj.test3205tj.domain.model.RepositoryInfo

interface GithubApi {
    @GET("/users/{username}/repos")
    suspend fun getSearchRepositories(
        @Path("username") query: String,
        @Query("per_page") perPage: Int = 100
    ): List<RepositoryInfo>

    @GET("/repos/{owner}/{repo}/zipball")
    suspend fun downloadRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): ResponseBody
}