
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.hbg.reethcast.R
import com.hbg.reethcast.data.entities.Song

@Composable
fun HomeScreen(
    navController: NavController,
) {

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
            SearchSection()

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                color = Color(0xFF221435),
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            SongGrid(
                songs = listOf(
                    Song("1", "Wrong Side Of Heaven", "Five Finger Death Punch", "", "https://i.discogs.com/JBQDNXkaMg3cTH8FHyN_BzJlAWFpZZvxv2foz5aJY1U/rs:fit/g:sm/q:90/h:528/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQ4MDk3/MzQtMTUzNTMyNTE5/Ni05MTA5LmpwZWc.jpeg"),
                    Song("2", "A Little Bit Off", "Five Finger Death Punch", "", "https://m.media-amazon.com/images/I/910guwe9zKL._UF350,350_QL50_.jpg" ),
                    Song("3", "Thanks For Asking", "Five Finger Death Punch", "","https://m.media-amazon.com/images/I/7121nd02oZL._UF894,1000_QL80_.jpg"),
                    Song("4", "Dead Butterflies", "Architects", "","https://m.media-amazon.com/images/I/61nPKFegg7L._UF894,1000_QL80_.jpg"),
                    Song("5", "Soft", "Motionless In White", "","https://m.media-amazon.com/images/I/71e7jHmIrWL._UF894,1000_QL80_.jpg"),
                    Song("6", "Sacrifice", "The Devil Wears Prada", "","https://i.scdn.co/image/ab67616d0000b2734b50b588c3142a93e0c4dc3e"),
                    Song("7", "Farewell II Flesh", "Ice Nine Kills", "","https://images.genius.com/8da3f4ae4f4b91dae1eb945c24c3abc7.1000x1000x1.jpg"),
                    Song("8", "The Vengeful One", "Disturbed", "","https://m.media-amazon.com/images/I/81gcQXWNsYL._UF1000,1000_QL80_.jpg"),
                    Song("9", "The Eagle Flies Alone", "Arch Enemy", "","https://i.scdn.co/image/ab67616d0000b2738692eeafeec91498e56b8817", ),
                    Song("10", "Painkiller", "Three Days Grace", "","https://i.scdn.co/image/ab67616d0000b2738443a724ced4e3bef303fb7a", ),
                    )
            ) { selectedSong ->
                navController.navigate("playerScreen/${selectedSong.mediaId}/${selectedSong.tittle}/${selectedSong.artist}/${selectedSong.songUrl}/${selectedSong.image}")
            }
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
            onClick = { /* TODO */ },
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
fun SongGrid(songs: List<Song>, onSongClicked: (Song) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)) {
        items(songs) { song ->
            SongItem(song = song, onSongClicked = onSongClicked)
        }
    }
}

@Composable
fun SongItem(song: Song, onSongClicked: (Song) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSongClicked(song) }
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = song.image,
                builder = {
                    scale(Scale.FIT)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .width(150.dp)
                .height(150.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.background)
        )
        Text(
            text = song.tittle,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )
        Text(
            text = song.artist,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 10.sp,
            ),
            color = Color.Gray
        )
    }
}