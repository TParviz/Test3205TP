package tj.test3205tj.domain.model

import com.google.gson.annotations.SerializedName

data class RepositoryInfo(
    val name: String,
    val owner: Owner,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String?,
    val language: String?,
)