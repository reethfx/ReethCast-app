package com.hbg.reethcast.navegation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "${ReethcastScreens.PlayerScreen.name}/{mediaId}/{title}/{artist}/{songUrl}",
            arguments = listOf(
                navArgument("mediaId") { type = NavType.StringType },
                navArgument("tittle") { type = NavType.StringType },
                navArgument("artist") { type = NavType.StringType },
                navArgument("songUrl") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId") ?: ""
            val tittle = backStackEntry.arguments?.getString("title") ?: ""
            val artist = backStackEntry.arguments?.getString("artist") ?: ""
            val songUrl = backStackEntry.arguments?.getString("songUrl") ?: ""
            val image = backStackEntry.arguments?.getString("image") ?: ""
        }
    }
}
