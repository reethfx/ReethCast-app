package com.hbg.reethcast.screens.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.size.Scale

@Composable
fun PlayerScreen(
    mediaId: String,
    title: String,
    artist: String,
    songUrl: String,
    image: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen grande de la canción
        Image(
            painter = rememberImagePainter(
                data = songUrl,
                builder = {
                    scale(Scale.FILL)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.background)
        )

        // Título de la canción
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

        // Artista de la canción
        Text(
            text = artist,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Gray
            )
        )

        // Línea de tiempo
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Gray)
        )

        // Botones de control de reproducción
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón de rebobinado
            IconButton(onClick = { /* Acción de rebobinado */ }) {
                // Icono de rebobinado
            }

            // Botón de pausa/reanudación
            IconButton(onClick = { /* Acción de pausa/reanudación */ }) {
                // Icono de pausa/reanudación
            }

            // Botón de adelanto
            IconButton(onClick = { /* Acción de adelanto */ }) {
                // Icono de adelanto
            }
        }
    }
}