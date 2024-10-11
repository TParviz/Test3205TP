package tj.test3205tj.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.test3205tj.data.repository.ApiRepositoryImpl
import tj.test3205tj.domain.model.RepositoryInfo
import javax.inject.Inject

data class GetRepository(val name: String)

interface GetRepositoriesUseCase : FlowUseCase<GetRepository, List<RepositoryInfo>>

class GetRepositoriesUseCaseImpl @Inject constructor(
    private val repository: ApiRepositoryImpl
) : GetRepositoriesUseCase {

    override fun execute(param: GetRepository): Flow<Result<List<RepositoryInfo>>> = flow {
        emit(Result.success(repository.getRepositories(param.name)))
    }
}