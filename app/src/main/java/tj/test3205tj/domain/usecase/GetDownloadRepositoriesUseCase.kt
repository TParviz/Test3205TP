package tj.test3205tj.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity
import tj.test3205tj.domain.repository.DbRepository
import javax.inject.Inject

interface GetDownloadRepositoriesUseCase : FlowUseCase<Unit, List<RepositoryDownloadEntity>>

class GetDownloadRepositoriesUseCaseImpl @Inject constructor(
    private val dbRepository: DbRepository
) : GetDownloadRepositoriesUseCase {

    override fun execute(param: Unit): Flow<Result<List<RepositoryDownloadEntity>>> = flow {
        emit(Result.success(dbRepository.getDownloadedRepositories()))
    }
}