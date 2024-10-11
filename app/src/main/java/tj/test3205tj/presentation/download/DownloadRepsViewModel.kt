package tj.test3205tj.presentation.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tj.test3205tj.domain.model.RepositoryListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.test3205tj.domain.usecase.GetDownloadRepositoriesUseCaseImpl
import tj.test3205tj.domain.model.toUi
import javax.inject.Inject

@HiltViewModel
class DownloadRepsViewModel @Inject constructor(
    private val getDownloadRepositoriesUseCaseImpl: GetDownloadRepositoriesUseCaseImpl
) : ViewModel() {

    val downloadedRepositoriesLiveData = MutableLiveData<List<RepositoryListItem>>()

    init {
        viewModelScope.launch {
            getDownloadRepositoriesUseCaseImpl(Unit).collect { result ->
                result.onSuccess { items ->
                    val list = items.map {
                        it.toUi()
                    }
                    downloadedRepositoriesLiveData.postValue(list)
                }
            }
        }
    }
}