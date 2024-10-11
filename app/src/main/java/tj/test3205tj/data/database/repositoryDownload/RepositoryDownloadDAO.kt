package tj.test3205tj.data.database.repositoryDownload

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDownloadDAO {
    @Query("SELECT * FROM repositorydownload")
    suspend fun getDownloadedRepositories(): List<RepositoryDownloadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepository(downloadRepositoryEntity: RepositoryDownloadEntity)
}