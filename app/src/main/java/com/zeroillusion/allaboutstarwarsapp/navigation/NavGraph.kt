package com.zeroillusion.allaboutstarwarsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroillusion.allaboutstarwarsapp.presentation.favorite.components.FavoriteScreen
import com.zeroillusion.allaboutstarwarsapp.presentation.main.components.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        modifier = modifier
    ) {
        composable(Screen.MainScreen.route) { MainScreen() }
        composable(Screen.FavoriteScreen.route) { FavoriteScreen() }
    }
}