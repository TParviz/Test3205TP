package tj.test3205tj.domain.usecase

import kotlinx.coroutines.flow.flow
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity
import tj.test3205tj.domain.repository.DbRepository
import javax.inject.Inject

interface SaveRepositoryDownloadUseCase : FlowUseCase<RepositoryDownloadEntity, Unit>

class SaveRepositoryDownloadUseCaseImpl @Inject constructor(
    private val dbRepository: DbRepository
) : SaveRepositoryDownloadUseCase {

    override fun execute(param: RepositoryDownloadEntity) = flow {
        emit(Result.success(dbRepository.saveRepositoryDownload(param)))
    }
}