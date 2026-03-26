package com.dlna

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.player.databinding.ActivityMainBinding
import org.fourthline.cling.model.meta.RemoteDevice

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player: ExoPlayer? = null

    private val devices = mutableListOf<RemoteDevice>()
    private lateinit var adapter: DeviceAdapter
    private lateinit var dlnaManager: DlnaManager

    private val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPlayer()

        adapter = DeviceAdapter(devices) { device ->
            dlnaManager.cast(device, videoUrl)
        }

        binding.deviceList.layoutManager = LinearLayoutManager(this)
        binding.deviceList.adapter = adapter

        dlnaManager = DlnaManager(this) { device ->
            runOnUiThread {
                devices.add(device)
                adapter.notifyItemInserted(devices.size - 1)
            }
        }

        binding.btnDiscover.setOnClickListener {
            dlnaManager.bindService()
        }
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}
