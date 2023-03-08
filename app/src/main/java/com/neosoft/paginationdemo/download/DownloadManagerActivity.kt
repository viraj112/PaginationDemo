package com.neosoft.paginationdemo.download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neosoft.paginationdemo.databinding.ActivityDownloadManagerBinding
import kotlin.math.roundToInt


class DownloadManagerActivity : AppCompatActivity() {

    lateinit var binding :ActivityDownloadManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.neosoft.paginationdemo.R.layout.activity_download_manager)

        binding.btnDownload.setOnClickListener {
            downloadFile()
        }

    }

    @SuppressLint("Range")
    private fun downloadFile() {

//        val downloadManager :DownloadManager.Request= DownloadManager.Request(Uri.parse("https://filesamples.com/samples/video/m4v/sample_960x540.m4v"))
//        downloadManager.setTitle("myVideo")
//        downloadManager.setDescription("Downloading please wait...")
//        val cookie = android.webkit.CookieManager.getInstance().getCookie("https://filesamples.com/samples/video/m4v/sample_960x540.m4v")
//        downloadManager.addRequestHeader("Cookie",cookie)
//        downloadManager.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//        downloadManager.setDestinationInExternalPublicDir("Download","video.mp4")
//        val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
//        manager.enqueue(downloadManager)
//
//        Toast.makeText(this,"Download Started",Toast.LENGTH_SHORT).show()
//

        val urlDownload =
            "https://filesamples.com/samples/video/m4v/sample_960x540.m4v"
        val request = DownloadManager.Request(Uri.parse(urlDownload))

        request.setDescription("Testando")
        request.setTitle("Download")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir("Download", "nnn.mp4")

        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadId = manager.enqueue(request)

        Thread {
            var downloading = true
            while (downloading) {
                val q = DownloadManager.Query()
                q.setFilterById(downloadId)
                val cursor: Cursor = manager.query(q)
                cursor.moveToFirst()
                val bytes_downloaded: Int = cursor.getInt(
                    cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                )
                val bytes_total: Int =
                    cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) === DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                //val dl_progress = (bytes_downloaded / bytes_total * 100).toDouble()
                val dl_progress: Float =  (bytes_downloaded * 100f / bytes_total)
                publishProgress(Integer.valueOf("" + ((bytes_downloaded * 100) / bytes_total)));
                // var dl_progress = ((bytes_downloaded * 100) / bytes_total);
                Log.d("MAIN_VIEW_ACTIVITY", "byteDownloaded $bytes_downloaded" +"bytes total $bytes_total")
//                runOnUiThread {
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        binding.progressbar.setProgress(dl_progress.toInt(),true)
//                    }
//                }
                statusMessage(cursor)?.let { Log.d("MAIN_VIEW_ACTIVITY", it) }
                cursor.close()
            }
        }.start()
    }

    private fun publishProgress(valueOf: Int) {
        //binding.progressbar.setProgress(dl_progress.toInt(),true)
        binding.progressbar.setProgress(valueOf);
    }

    @SuppressLint("Range")
    private fun statusMessage(c: Cursor): String? {
        var msg = "???"
        msg =
            when (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_FAILED -> "Download failed!"
                DownloadManager.STATUS_PAUSED -> "Download paused!"
                DownloadManager.STATUS_PENDING -> "Download pending!"
                DownloadManager.STATUS_RUNNING -> "Download in progress!"
                DownloadManager.STATUS_SUCCESSFUL -> "Download complete!"
                else -> "Download is nowhere in sight"
            }
        return msg
    }
}