package tj.test3205tj.data.database.repositoryDownload

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "repositorydownload", indices = [Index(value = ["id"], unique = true)])
data class RepositoryDownloadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val login: String,
    val url: String,
    val isDownloaded: Int = 0,
    val language: String = "None"
)