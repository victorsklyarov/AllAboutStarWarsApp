package com.zeroillusion.allaboutstarwarsapp.presentation.favorite.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zeroillusion.allaboutstarwarsapp.presentation.favorite.FavoriteViewModel
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.PersonCard
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.PlanetCard
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.StarshipCard
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.getAllFavorites()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            LaunchedEffect(key1 = true) {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is FavoriteViewModel.UIEvent.ShowSnackbar -> {
                            snackbarHostState.showSnackbar(
                                message = event.message
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(2)
                ) {
                    items(state.favoritePeople.size) { index ->
                        Column {
                            PersonCard(
                                person = state.favoritePeople[index],
                                onFavoriteClick = {
                                    viewModel.changeFavoriteStatus(state.favoritePeople[index])
                                }
                            )
                        }
                    }
                    items(state.favoriteStarships.size) { index ->
                        Column {
                            StarshipCard(
                                starship = state.favoriteStarships[index],
                                onFavoriteClick = {
                                    viewModel.changeFavoriteStatus(state.favoriteStarships[index])
                                }
                            )
                        }
                    }
                    items(state.favoritePlanets.size) { index ->
                        Column {
                            PlanetCard(
                                planet = state.favoritePlanets[index],
                                onFavoriteClick = {
                                    viewModel.changeFavoriteStatus(state.favoritePlanets[index])
                                }
                            )
                        }
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}