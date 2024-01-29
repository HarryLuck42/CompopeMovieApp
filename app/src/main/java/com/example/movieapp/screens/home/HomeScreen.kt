package com.example.movieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.components.DrawerBody
import com.example.movieapp.components.DrawerHeader
import com.example.movieapp.components.dialog.SearchDialog
import com.example.movieapp.components.dialog.ShowDialog
import com.example.movieapp.model.MenuItem
import com.example.movieapp.screens.home.fragments.NowPlayingFragment
import com.example.movieapp.screens.home.fragments.PopularFragment
import com.example.movieapp.screens.home.fragments.UpcomingFragment
import com.example.movieapp.screens.home.fragments.favorites.FavoritesFragment
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val menus = listOf(
        MenuItem(
            id = Constants.NOW_PLAYING_PATH,
            title = "Now Playing",
            contentDescription = "Go to Now Playing page",
            icon = painterResource(id = R.drawable.now_playing)
        ),
        MenuItem(
            id = Constants.POPULAR_PATH,
            title = "Popular",
            contentDescription = "Go to Popular page",
            icon = painterResource(id = R.drawable.popular)
        ),
        MenuItem(
            id = Constants.UPCOMING_PATH,
            title = "Upcoming",
            contentDescription = "Go to Upcoming page",
            icon = painterResource(id = R.drawable.upcoming)
        ),
        MenuItem(
            id = Constants.FAVORITES_PATH,
            title = "Favorites",
            contentDescription = "Go to Favorites page",
            icon = painterResource(id = R.drawable.fill_star)
        )
    )
    var page by remember{
        mutableStateOf(menus[0])
    }

    if(viewModel.openSearch.value){
        ShowDialog(openDialog = viewModel.openSearch) {
            SearchDialog(controller = viewModel.openSearch, valueState = viewModel.keyword) {
                viewModel.openSearch.value = false
                Log.d("Open Search", "Result search: ${viewModel.keyword.value}")
            }
        }
    }


    
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name))},
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = { IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }){

                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Toggle drawer"
                    )
                } },
                actions = {
                    IconButton(onClick = { 
                        viewModel.openSearch.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Action"
                        )
                    }
                    
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
        DrawerHeader()
        DrawerBody(menus = menus, onItemClick = {
            scope.launch {
                scaffoldState.drawerState.close()
            }
            viewModel.keyword.value = ""
            page = it
            println("clicked page ${it.title}")
        },
        )
    }){


        when(page.id){
            Constants.NOW_PLAYING_PATH ->{
                NowPlayingFragment(viewModel, navController)
            }
            Constants.POPULAR_PATH ->{
                PopularFragment(viewModel, navController)
            }
            Constants.UPCOMING_PATH ->{
                UpcomingFragment(viewModel, navController)
            }
            Constants.FAVORITES_PATH ->{
                FavoritesFragment(viewModel, navController)
            }
        }
    }
}