package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlindemo.Compose.MyComposeActivity
import com.example.kotlindemo.Flow.FlowActivity
import com.example.kotlindemo.Gallery.GalleryActivity
import com.example.kotlindemo.Tutorial.MainKotlinActivity
import com.example.kotlindemo.Services.WorkManagerActivity
import com.example.kotlindemo.coroutine.CoroutineActivity

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnKotlin -> startActivity(Intent(this, MainKotlinActivity::class.java))
            R.id.btnCoroutine -> startActivity(Intent(this, CoroutineActivity::class.java))
            R.id.btnWorkManager -> startActivity(Intent(this, WorkManagerActivity::class.java))
            R.id.btnFlow -> startActivity(Intent(this, FlowActivity::class.java))
            R.id.btnJetpackCompose -> startActivity(Intent(this, MyComposeActivity::class.java))
            R.id.btnGallery -> startActivity(Intent(this, GalleryActivity::class.java))
        }
    }
}