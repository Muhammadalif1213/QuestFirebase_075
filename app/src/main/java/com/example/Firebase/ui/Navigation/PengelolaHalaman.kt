package com.example.Firebase.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Firebase.ui.view.DetailView
import com.example.Firebase.ui.view.HomeScreen
import com.example.Firebase.ui.view.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsert.route)},
            )
        }
        composable(DestinasiInsert.route){
            InsertMhsView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.navigate(DestinasiHome.route)})

        }
        composable(DestinasiDetail.route) {
            DetailView(
                onEditClick = { },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}