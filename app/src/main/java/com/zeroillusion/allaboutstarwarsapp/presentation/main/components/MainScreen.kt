package com.zeroillusion.allaboutstarwarsapp.presentation.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.PersonCard
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.PlanetCard
import com.zeroillusion.allaboutstarwarsapp.presentation.general_components.StarshipCard
import com.zeroillusion.allaboutstarwarsapp.presentation.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            LaunchedEffect(key1 = true) {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is MainViewModel.UIEvent.ShowSnackbar -> {
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
                Column(modifier = Modifier.fillMaxSize()) {
                    var searchText by remember { mutableStateOf("") }
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            if (searchText.length > 1) viewModel.searchByName(searchText)
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        },
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = { searchText = "" }) {
                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (searchText.length > 1) {
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = StaggeredGridCells.Fixed(2)
                        ) {
                            items(state.people.size) { index ->
                                Column {
                                    PersonCard(
                                        person = state.people[index],
                                        onFavoriteClick = {
                                            viewModel.changeFavoriteStatus(state.people[index])
                                        }
                                    )
                                }
                            }
                            items(state.starships.size) { index ->
                                Column {
                                    StarshipCard(
                                        starship = state.starships[index],
                                        onFavoriteClick = {
                                            viewModel.changeFavoriteStatus(state.starships[index])
                                        }
                                    )
                                }
                            }
                            items(state.planets.size) { index ->
                                Column {
                                    PlanetCard(
                                        planet = state.planets[index],
                                        onFavoriteClick = {
                                            viewModel.changeFavoriteStatus(state.planets[index])
                                        }
                                    )
                                }
                            }
                        }
                    } else viewModel.resetState()
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}