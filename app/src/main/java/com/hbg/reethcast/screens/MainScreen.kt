package com.hbg.reethcast.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.hbg.reethcast.navegation.ReethcastScreens
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1200L)
        navController.navigate(ReethcastScreens.LoginScreen.name)
    }
}