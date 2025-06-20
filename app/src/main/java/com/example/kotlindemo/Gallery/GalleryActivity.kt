package com.example.kotlindemo.Gallery

import android.Manifest
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivityGalleryBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class GalleryActivity : AppCompatActivity() {

    private var _binding: ActivityGalleryBinding? = null
    private val binding: ActivityGalleryBinding get() = _binding!!
    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var mediaAdapter: MediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupObservers()
        requestPermission()
    }

    private fun requestPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
        } else {
            listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        Dexter.withContext(this).withPermissions(permission)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    p0?.let {
                        if (p0.areAllPermissionsGranted()) {
                            viewModel.loadMedia()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest();
                }
            })
            .withErrorListener {
                Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
            }.check()

    }

    private fun setupObservers() {
        viewModel.mediaItem.observe(this) {
            mediaAdapter.submitNewList(it)
        }

        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.error.observe(this) {
            it?.let {
                Toast.makeText(this@GalleryActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
//        binding.recyclerView.layoutManager = GridLayoutManager(this, 3) // 3 columns
//        binding.recyclerView.adapter = MediaAdapter(mediaList)
//
        mediaAdapter = MediaAdapter {
            Toast.makeText(this@GalleryActivity, it.name, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@GalleryActivity, 3)
            adapter = mediaAdapter
//            addItemDecoration(
//                GridSpacingItemDecoration(
//                    spanCount = 3,
//                    spacing = 8.dpToPx(),
//                    includeEdge = true
//                )
//            )
        }
    }

    private fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}