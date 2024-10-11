package tj.test3205tj.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tj.test3205tj.databinding.RepositoryListItemBinding
import tj.test3205tj.domain.model.RepositoryListItem

class RepositoryAdapter(
    private val onItemClick: (repository: RepositoryListItem) -> Unit,
    private val onDownloadItemClick: (repository: RepositoryListItem) -> Unit
) : ListAdapter<RepositoryListItem, RepositoryAdapter.RepositoryViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            binding = RepositoryListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            ),
            onItemClick = onItemClick,
            onDownloadItemClick = onDownloadItemClick
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepositoryViewHolder(
        private val onItemClick: (repository: RepositoryListItem) -> Unit,
        private val onDownloadItemClick: (repository: RepositoryListItem) -> Unit,
        private val binding: RepositoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryListItem) = with(binding) {

            itemView.setOnClickListener {
                onItemClick(item)
            }

            ivDownload.setOnClickListener {
                onDownloadItemClick(item)
                ivDownload.isVisible = false
                pbDownload.isVisible = true
            }
            tvRepositoryName.text = item.ownerName + " / " + item.name
            tvRepositoryLanguage.text = "Language: " + item.language
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepositoryListItem>() {

            override fun areItemsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: RepositoryListItem,
                newItem: RepositoryListItem
            ) = oldItem == newItem
        }
    }
}

