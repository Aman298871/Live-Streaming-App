package com.example.mylivestramingapp

// Permissions
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import java.util.Random

class MainActivity : AppCompatActivity() {

    // Buttons
    private lateinit var startlivebtn: Button
    private lateinit var joinlivebtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect activity with XML
        setContentView(R.layout.activity_main)

        // Connect buttons with XML IDs
        startlivebtn = findViewById(R.id.startlivebtn)
        joinlivebtn = findViewById(R.id.joinlivebtn)

        // Ask camera & mic permissions
        requestPermissions()

        // Your Zego App ID
        val appID: Long = 1167151422

        // Your Zego App Sign
        val appSign =
            "1ce6cb328391b8d870d565976dab6d459b087c64c134e88827c0b0fddc43a6de"

        // Generate random user ID
        val userID = generateUserID()

        // User display name
        val userName = "User_$userID"

        // Room ID
        // IMPORTANT:
        // Avoid spaces/special characters
        val liveID = "testlive123"

        // Start Live Button
        startlivebtn.setOnClickListener {

            // Open LiveActivity
            val intent = Intent(this, LiveActivity::class.java)

            // Send data to next activity

            // User is host
            intent.putExtra("host", true)

            intent.putExtra("appID", appID)

            intent.putExtra("appSign", appSign)

            intent.putExtra("userID", userID)

            intent.putExtra("userName", userName)

            intent.putExtra("liveID", liveID)

            startActivity(intent)
        }

        // Join Live Button
        joinlivebtn.setOnClickListener {

            val intent = Intent(this, LiveActivity::class.java)

            // User is audience
            intent.putExtra("host", false)

            intent.putExtra("appID", appID)

            intent.putExtra("appSign", appSign)

            intent.putExtra("userID", userID)

            intent.putExtra("userName", userName)

            intent.putExtra("liveID", liveID)

            startActivity(intent)
        }
    }

    // Function to ask camera & microphone permissions
    private fun requestPermissions() {

        // Check if permissions are already granted
        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED ||

            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Ask permissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                ),
                1
            )
        }
    }

    // Generate random 5 digit user ID
    private fun generateUserID(): String {

        val random = Random()

        return (10000 + random.nextInt(90000)).toString()
    }
}