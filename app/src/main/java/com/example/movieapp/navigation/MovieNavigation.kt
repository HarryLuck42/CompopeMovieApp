package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.screens.MovieSplashScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.SplashScreen.name){
        composable(MovieScreens.SplashScreen.name){
            MovieSplashScreen(navController = navController)
        }
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
    }


}