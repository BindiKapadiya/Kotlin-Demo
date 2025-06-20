package com.example.kotlindemo.Gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemo.databinding.ItemMediaBinding

class MediaAdapter(private val onItemClick: (MediaItem) -> Unit) :
    ListAdapter<MediaItem, MediaAdapter.GalleryItemViewHolder>(MediaDiffCallback()) {

    private var mediaItems = emptyList<MediaItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return GalleryItemViewHolder(
            ItemMediaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mediaItems.size

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(mediaItems[position])
    }

    fun submitNewList(newList: List<MediaItem>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = mediaItems.size
            override fun getNewListSize(): Int = newList.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
                mediaItems[oldPos].uri == newList[newPos].uri
            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
                mediaItems[oldPos] == newList[newPos]
        })

        mediaItems = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class GalleryItemViewHolder(val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaItem: MediaItem) {
            binding.mediaName.text = mediaItem.name

            binding.mediaInfo.text = buildString {
                if (mediaItem.isImage) {
                    append("Image")
                } else {
                    append("Video • ${formatDuration(mediaItem.duration)}")
                }
                append(" • ${formatFileSize(mediaItem.size)}")
            }

            Glide.with(itemView.context).load(mediaItem.uri).centerCrop()
                .into(binding.mediaThumbnail)

            // Show video icon only for videos
//            binding.videoIcon.visibility = if (mediaItem.isImage) View.GONE else View.VISIBLE

            // Set click listener with debounce to prevent double clicks
            binding.root.setOnClickListener {
                binding.root.isClickable = false
                binding.root.postDelayed({ binding.root.isClickable = true }, 300)
                onItemClick(mediaItem)
            }
        }
    }


    private fun formatFileSize(size: Long): String {
        return when {
            size >= 1024 * 1024 -> "%.1f MB".format(size.toFloat() / (1024 * 1024))
            size >= 1024 -> "%.1f KB".format(size.toFloat() / 1024)
            else -> "$size B"
        }
    }

    private fun formatDuration(duration: Long): String {
        val seconds = duration / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        return if (hours > 0) {
            "%02d:%02d:%02d".format(hours, minutes % 60, seconds % 60)
        } else {
            "%02d:%02d".format(minutes % 60, seconds % 60)
        }
    }

}