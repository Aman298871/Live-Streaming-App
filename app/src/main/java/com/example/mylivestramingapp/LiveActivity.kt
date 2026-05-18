package com.example.mylivestramingapp

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment

class LiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect XML layout
        setContentView(R.layout.activity_live)

        // Start live streaming fragment
        startLive()
    }

    private fun startLive() {

        // Receive all data from MainActivity

        val appID = intent.getLongExtra("appID", 0L)

        val appSign = intent.getStringExtra("appSign") ?: ""

        val userID = intent.getStringExtra("userID") ?: ""

        val userName = intent.getStringExtra("userName") ?: ""

        val liveID = intent.getStringExtra("liveID") ?: ""

        // Check whether user is host or audience
        val isHost = intent.getBooleanExtra("host", false)

        // Show room ID
        Toast.makeText(
            this,
            "Joining Room: $liveID",
            Toast.LENGTH_SHORT
        ).show()

        // Host configuration
        // Audience configuration
        val config =
            if (isHost) {

                // User can stream
                ZegoUIKitPrebuiltLiveStreamingConfig.host()

            } else {

                // User can only watch
                ZegoUIKitPrebuiltLiveStreamingConfig.audience()
            }

        // Create Zego Live Streaming Fragment
        val fragment =
            ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
                appID,
                appSign,
                userID,
                userName,
                liveID,
                config
            )

        // Put fragment inside FrameLayout
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}