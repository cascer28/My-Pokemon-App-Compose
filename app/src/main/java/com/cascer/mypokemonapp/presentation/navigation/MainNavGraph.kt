package com.cascer.mypokemonapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cascer.mypokemonapp.presentation.screen.detail.DetailScreen
import com.cascer.mypokemonapp.presentation.screen.home.HomeScreen
import com.cascer.mypokemonapp.presentation.screen.profile.ProfileScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(Routes.HOME) { HomeScreen(navController) }
    composable(Routes.PROFILE) { ProfileScreen(navController) }
    composable(
        route = Routes.DETAIL,
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id") ?: 0
        DetailScreen(id = id)
    }
}