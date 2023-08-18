package com.zeroillusion.allaboutstarwarsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Constants.FAVORITE_SCREEN
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Constants.MAIN_SCREEN

sealed class Screen(val route: String, val icon: ImageVector) {

    object MainScreen : Screen(MAIN_SCREEN, Icons.Default.Home)
    object FavoriteScreen : Screen(FAVORITE_SCREEN, Icons.Default.Favorite)
}