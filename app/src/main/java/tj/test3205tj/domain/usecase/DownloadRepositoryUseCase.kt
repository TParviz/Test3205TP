package tj.test3205tj.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import tj.test3205tj.data.repository.ApiRepositoryImpl
import javax.inject.Inject

data class DownloadRepository(val name: String, val repository: String)

interface DownloadRepositoryUseCase : FlowUseCase<DownloadRepository, ResponseBody>

class DownloadRepositoryUseCaseImpl @Inject constructor(
    private val repository: ApiRepositoryImpl
) : DownloadRepositoryUseCase {

    override fun execute(param: DownloadRepository): Flow<Result<ResponseBody>> = flow {
        emit(Result.success(repository.downloadRepository(param.name, param.repository)))
    }
}