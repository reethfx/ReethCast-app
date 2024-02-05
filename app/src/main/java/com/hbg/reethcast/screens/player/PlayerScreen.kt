package com.hbg.reethcast.screens.player

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun PlayerScreen(
    navController: NavController,
    mediaId: String?,
    tittle: String?,
    artist: String?,
    songUrl: String?,
    imageUrl: String?
) {
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var duration by remember { mutableStateOf(0) }
    var currentPosition by remember { mutableStateOf(0) }
    Log.d("Reethcast", "Reproduciendo audio desde: $songUrl")


    DisposableEffect(songUrl) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(songUrl)

        mediaPlayer?.setOnPreparedListener {
            duration = mediaPlayer?.duration ?: 0
            if (isPlaying) {
                mediaPlayer?.start()
            }
        }

        mediaPlayer?.setOnCompletionListener {
            isPlaying = false
            currentPosition = 0
            mediaPlayer?.seekTo(0)
        }

        mediaPlayer?.prepareAsync()

        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFF0D031F)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = imageUrl, builder = {
                    crossfade(true)
                }),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .width(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top = 25.dp)
            ) {
                Text(
                    text = tittle ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = artist ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Slider
                PlaybackControls(isPlaying, duration, currentPosition) {
                    isPlaying = !isPlaying
                    if (isPlaying) {
                        mediaPlayer?.start()
                    } else {
                        mediaPlayer?.pause()
                    }
                }
            }
        }
    }
}

@Composable
fun PlaybackControls(isPlaying: Boolean, duration: Int, currentPosition: Int, onPlayPauseToggle: () -> Unit) {
    var progress by remember { mutableStateOf(0.0f) }

    Slider(
        value = progress,
        valueRange = 0f..duration.toFloat(),
        onValueChange = { progress = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray,
            )
        )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .size(40.dp)
                .background(Color.Transparent, shape = CircleShape)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.FastRewind,
                contentDescription = "Rewind 10 seconds",
                tint = Color.White
            )
        }


        IconButton(
            onClick = { onPlayPauseToggle() },
            modifier = Modifier.size(40.dp)
        ) {
            val icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow
            Icon(
                imageVector = icon,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color(0xFFededed)
            )
        }

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .size(40.dp)
                .background(Color.Transparent, shape = CircleShape)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.FastForward,
                contentDescription = "Fast Forward 10 seconds",
                tint = Color.White
            )
        }
    }
}


