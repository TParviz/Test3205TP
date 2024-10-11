package tj.test3205tj.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.File

class RepositoryDownloader(
    private val context: Context,
    private val responseBody: ResponseBody,
    private val fileName: String,
    private val onCompleteDownload: () -> Unit,
) {

    private suspend fun ResponseBody.saveFile() {
        withContext(Dispatchers.IO) {
            val downloadsFolder =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val destinationFile = File(downloadsFolder, "$fileName.zip")

            try {
                byteStream().use { inputStream ->
                    destinationFile.outputStream().use { outputStream ->
                        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                        var progressBytes = 0L
                        var bytes = inputStream.read(buffer)
                        while (bytes >= 0) {
                            outputStream.write(buffer, 0, bytes)
                            progressBytes += bytes
                            bytes = inputStream.read(buffer)
                        }
                    }
                }
                onCompleteDownload()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun downloadFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            responseBody.saveFile()
        } else {
            if (checkPermission()) {
                responseBody.saveFile()
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 42
            )
        }
    }
}