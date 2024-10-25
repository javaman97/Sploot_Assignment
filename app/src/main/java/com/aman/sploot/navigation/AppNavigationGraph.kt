package com.aman.sploot.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aman.sploot.views.article_screen.ArticleScreen
import com.aman.sploot.viewmodels.NewsViewModel
import com.aman.sploot.views.newsScreen.NewsScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    val argKey = "web_url"
    NavHost(navController = navController , startDestination = Routes.NEWS_SCREEN){

        composable(route = Routes.NEWS_SCREEN) {
            val viewModel: NewsViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClick = { url ->
                    navController.navigate("${Routes.ARTICLE_SCREEN}?$argKey=$url")
                }
            )
        }
        composable(
            route = "${Routes.ARTICLE_SCREEN}?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ArticleScreen(
                url = backStackEntry.arguments?.getString(argKey),
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}