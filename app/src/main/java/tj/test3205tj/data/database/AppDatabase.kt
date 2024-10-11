package tj.test3205tj.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadDAO
import tj.test3205tj.data.database.repositoryDownload.RepositoryDownloadEntity

@Database(entities = [RepositoryDownloadEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repositoryDownloadDao(): RepositoryDownloadDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "downloads_table"
            ).build()
        }
    }
}