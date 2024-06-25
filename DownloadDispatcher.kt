package com.example.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DownloadDispatcher : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val FileID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if (FileID != -1L) {
                Toast.makeText(context, "Download $FileID completed", Toast.LENGTH_LONG).show()
            }
        }
    }
}
