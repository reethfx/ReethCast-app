package com.hbg.reethcast.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hbg.reethcast.screens.home.HomeScreen
import com.hbg.reethcast.screens.home.MainScreen
import com.hbg.reethcast.screens.login.LoginScreen

@Composable
fun ReethcastNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController ,
        startDestination = ReethcastScreens.MainScreen.name)
    {
        composable(ReethcastScreens.MainScreen.name){
            MainScreen(navController = navController)
        }
        composable(ReethcastScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(ReethcastScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
    }
}
