package tj.test3205tj.domain.model

import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity

data class RepositoryListItem(
    val name: String,
    val ownerName: String,
    val url: String,
    val language: String
)

fun RepositoryListItem.toEntity() = RepositoryDownloadEntity(
    name = this.name,
    login = this.ownerName,
    url = this.url,
    language = this.language
)

fun RepositoryInfo.toListItem() = RepositoryListItem(
    name = this.name,
    ownerName = this.owner.login,
    url = this.htmlUrl,
    language = this.language.toString()
)

fun RepositoryDownloadEntity.toUi() = RepositoryListItem(
    name = this.name,
    ownerName = this.login,
    url = this.url,
    language = this.language
)