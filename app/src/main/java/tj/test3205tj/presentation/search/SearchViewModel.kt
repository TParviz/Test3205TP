package tj.test3205tj.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import tj.test3205tj.domain.usecase.DownloadRepository
import tj.test3205tj.domain.usecase.DownloadRepositoryUseCaseImpl
import tj.test3205tj.domain.usecase.GetRepositoriesUseCaseImpl
import tj.test3205tj.domain.usecase.GetRepository
import tj.test3205tj.domain.model.RepositoryListItem
import tj.test3205tj.domain.model.toEntity
import tj.test3205tj.domain.model.toListItem
import tj.test3205tj.domain.usecase.SaveRepositoryDownloadUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepositoriesUseCaseImpl: GetRepositoriesUseCaseImpl,
    private val downloadRepositoryUseCaseImpl: DownloadRepositoryUseCaseImpl,
    private val saveRepositoryDownloadUseCase: SaveRepositoryDownloadUseCase
) : ViewModel() {

    val queryFlow = MutableStateFlow(value = "tparviz")
    val downloadingFileResponse = MutableLiveData<ResponseBody>()

    val repositoryListItemsFlow =
        queryFlow.debounce(300).flatMapLatest { query ->
            getRepositoriesUseCaseImpl(GetRepository(query))
        }.map { list ->
            val newList = mutableListOf<RepositoryListItem>()
            list.map { item ->
                item.forEach {
                    newList.add(it.toListItem())
                }
            }
            return@map newList
        }.catch {
            emit(mutableListOf())
        }


    fun downloadRepository(repo: RepositoryListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                downloadRepositoryUseCaseImpl(DownloadRepository(repo.ownerName, repo.name))
                    .collect { response ->
                        response.map {
                            downloadingFileResponse.postValue(it)
                        }
                    }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun saveDownloads(repository: RepositoryListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            saveRepositoryDownloadUseCase.execute(repository.toEntity()).collectLatest { response ->
                response.onFailure {
                    Log.d("TAG_SAVE", "FAILURE: $it")
                }.onSuccess {
                    Log.d("TAG_SAVE", "SUCCESS: $it")
                }
            }
        }
    }
}