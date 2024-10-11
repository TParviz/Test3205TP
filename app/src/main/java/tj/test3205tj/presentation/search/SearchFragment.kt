package tj.test3205tj.presentation.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.test3205tj.R
import tj.test3205tj.databinding.FragmentSearchBinding
import tj.test3205tj.domain.model.RepositoryListItem
import tj.test3205tj.extensions.RepositoryDownloader
import tj.test3205tj.presentation.search.adapter.RepositoryAdapter

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    private val binding by viewBinding(FragmentSearchBinding::bind)

    private var repositoryAdapter = RepositoryAdapter(
        onItemClick = {
            openRepositoryInBrowser(it.url)
        },
        onDownloadItemClick = {
            downloadFile(it)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initViews() = with(binding) {
        progressBar.isVisible = true
        rvRepositories.adapter = repositoryAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    viewModel.queryFlow.value = ""
                } else {
                    viewModel.queryFlow.value = newText.toString()
                }
                return true
            }
        })
    }

    private fun initObservers() = with(viewModel) {
        lifecycleScope.launch {
            repositoryListItemsFlow.collectLatest {
                binding.progressBar.isVisible = false
                repositoryAdapter.submitList(it)
            }
        }
    }

    private fun downloadFile(repository: RepositoryListItem) {
        viewModel.downloadRepository(repository)
        viewModel.downloadingFileResponse.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                RepositoryDownloader(
                    context = requireContext(),
                    responseBody = it,
                    fileName = repository.name,
                    onCompleteDownload = {
                        showToast()
                        viewModel.saveDownloads(repository)
                    }).downloadFile()
            }
        }
    }

    private fun showToast() {
        activity?.runOnUiThread {
            Toast.makeText(activity, "Downloaded Repository", Toast.LENGTH_SHORT).show()
            repositoryAdapter.notifyDataSetChanged()
        }
    }

    private fun openRepositoryInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}