package com.zeroillusion.allaboutstarwarsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zeroillusion.allaboutstarwarsapp.navigation.BottomNavigationBar
import com.zeroillusion.allaboutstarwarsapp.navigation.NavGraph
import com.zeroillusion.allaboutstarwarsapp.navigation.Screen
import com.zeroillusion.allaboutstarwarsapp.ui.theme.AllAboutStarWarsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllAboutStarWarsAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                Screen.MainScreen,
                                Screen.FavoriteScreen
                            ),
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}