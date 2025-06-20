package com.example.kotlindemo.Gallery

import android.net.Uri

data class MediaItem(
    val uri: Uri,
    val name: String,
    val dateAdded: Long,
    val mimeType: String,
    val size: Long,
    val isImage: Boolean,
    val duration: Long = 0 // Only for videos
)