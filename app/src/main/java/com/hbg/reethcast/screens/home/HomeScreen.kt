package com.hbg.reethcast.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hbg.reethcast.R
import com.hbg.reethcast.data.entities.Song

@Composable
fun HomeScreen(navController: NavController, viewModel: SongViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    DisposableEffect(Unit) {
        onDispose {
            viewModel.loadSongs()
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
                .padding(16.dp)
        ) {
            // Sección de búsqueda en la parte superior
            SearchSection()

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                color = Color(0xFF221435), // Color más oscuro
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            SongsGrid(songs = viewModel.songs)
        }
    }
}
@Composable
fun SearchSection() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), Arrangement.SpaceBetween, Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_avatar),
            contentDescription = "User menú",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    var isDrawerOpen = true
                }
                .padding(end = 8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Tu biblioteca",
            color = Color(0xFFededed),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color(0xFFededed)
            )
        }

        IconButton(
            onClick = { /* Acción al hacer clic en el icono de suma */ },
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir",
                tint = Color(0xFFededed)
            )
        }
    }
}

@Composable
fun SongsGrid(songs: List<Song>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(songs.size) { index ->
            val song = songs[index]
            SongCard(song = song)
        }
    }
}

@Composable
fun SongCard(song: Song) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { }
    ) {
        Image(
            painter = painterResource(id = song.image.toInt()),
            contentDescription = "Imagen de la canción",
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = song.tittle,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFededed),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = song.artist,
            fontSize = 12.sp,
            color = Color(0xFF808080),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}



