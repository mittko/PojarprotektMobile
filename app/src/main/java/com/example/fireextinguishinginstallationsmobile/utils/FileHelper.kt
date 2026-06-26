package com.example.fireextinguishinginstallationsmobile.utils

// Saves the Retrofit ResponseBody safely into the public Downloads folder
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import okhttp3.ResponseBody
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.Q)
fun savePdfToMediaStore(context: Context, body: ResponseBody, fileName: String): Uri? {
    val resolver = context.contentResolver

    // Дефинираме метаданните за файла в Android 14
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        // Записва се в споделената папка Downloads
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    // Вмъкваме записа в MediaStore базата данни
    val uri =
        resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues) ?: return null

    try {
        val inputStream: InputStream = body.byteStream()
        // Отваряме стрийм директно към новия Uri
        resolver.openOutputStream(uri)?.use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        return uri // Връщаме системния content:// URI
    } catch (e: Exception) {
        e.printStackTrace()
        // Ако нещо се счупи, трием записа, за да няма празен файл
        resolver.delete(uri, null, null)
        return null
    }
}

// Launches an external PDF viewer using FileProvider
fun openPdfFile(context: Context, uri: Uri) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(Intent.createChooser(intent, "Open PDF With").apply {
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}