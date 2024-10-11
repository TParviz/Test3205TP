package tj.test3205tj.presentation.download

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import tj.test3205tj.presentation.search.adapter.RepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import tj.test3205tj.R
import tj.test3205tj.databinding.FragmentDownloadRepsBinding

@AndroidEntryPoint
class DownloadRepsFragment : Fragment(R.layout.fragment_download_reps) {

    private val binding by viewBinding(FragmentDownloadRepsBinding::bind)

    private val viewModel by viewModels<DownloadRepsViewModel>()

    private val repositoryAdapter = RepositoryAdapter(
        onItemClick = {
            openRepositoryInBrowser(it.url)
        },
        onDownloadItemClick = {
            Toast.makeText(requireContext(), "Already downloaded", Toast.LENGTH_SHORT).show()
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.rvRepositories.adapter = repositoryAdapter
    }

    private fun initObservers() = with(viewModel) {
        downloadedRepositoriesLiveData.observe(viewLifecycleOwner) {
            repositoryAdapter.submitList(it)
        }
    }

    private fun openRepositoryInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}