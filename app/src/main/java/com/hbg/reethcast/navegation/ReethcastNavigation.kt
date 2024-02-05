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
import com.hbg.reethcast.screens.player.PlayerScreen

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
        composable("${ReethcastScreens.PlayerScreen.name}/{mediaId}/{tittle}/{artist}/{songUrl}/{image}/{duration}",
            arguments = listOf(
                navArgument("mediaId") { type = NavType.StringType },
                navArgument("tittle") { type = NavType.StringType },
                navArgument("artist") { type = NavType.StringType },
                navArgument("songUrl") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType },
                navArgument("duration") { type = NavType.StringType }
                )
        ) { backStackEntry ->
            PlayerScreen(navController,
                backStackEntry.arguments?.getString("mediaId"),
                backStackEntry.arguments?.getString("tittle"),
                backStackEntry.arguments?.getString("artist"),
                backStackEntry.arguments?.getString("songUrl"),
                backStackEntry.arguments?.getString("image"),
                backStackEntry.arguments?.getString("duration"),
            )

            val songUrl = backStackEntry.arguments?.getString("songUrl") ?: ""
            val image = backStackEntry.arguments?.getString("image") ?: ""
        }
    }
}
