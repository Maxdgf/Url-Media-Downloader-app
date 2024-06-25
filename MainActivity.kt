package com.example.downloader

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import android.os.Environment
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

lateinit var btnDownload: Button
lateinit var inputUrl: EditText
lateinit var preview: WebView
lateinit var inputType: EditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDownload = findViewById(R.id.btnStart)
        inputUrl = findViewById(R.id.inputLink)
        preview = findViewById(R.id.web)
        inputType = findViewById(R.id.inputFileType)

        btnDownload.setOnClickListener {
            startDownload()
        }

    }

    private fun startDownload() {
        val LinkUrl = inputUrl.text.toString()
        val FileType = inputType.text.toString()
        preview.loadUrl(LinkUrl)
        preview.settings.javaScriptEnabled = true
        preview.webViewClient = WebViewClient()
        val managerDownload = applicationContext.getSystemService(DownloadManager::class.java)
        val request = DownloadManager.Request(LinkUrl.toUri())
            .setMimeType(FileType)
            .setTitle("downloaded file")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Downloaded file")
        managerDownload.enqueue(request)
    }
}
