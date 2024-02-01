package com.hbg.reethcast.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.hbg.reethcast.R
import com.hbg.reethcast.navegation.ReethcastScreens
import kotlinx.coroutines.delay
@Composable
fun MainScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1200L)
        navController.navigate(ReethcastScreens.LoginScreen.name)

        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(ReethcastScreens.LoginScreen.name)
        } else {
            navController.navigate(ReethcastScreens.HomeScreen.name)
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
                .padding(16.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_logo_reethcast),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}
