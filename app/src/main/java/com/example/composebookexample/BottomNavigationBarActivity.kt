package com.example.composebookexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composebookexample.bottomscreens.Contacts
import com.example.composebookexample.bottomscreens.Favorites
import com.example.composebookexample.bottomscreens.Home
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class BottomNavigationBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen9()
                }
            }
        }
    }
}

@Composable
fun MainScreen9() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Bottom Navigation Demo") }) },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(BottomNavRoutes.Home.route) {
            Home()
        }

        composable(BottomNavRoutes.Contacts.route) {
            Contacts()
        }

        composable(BottomNavRoutes.Favorites.route) {
            Favorites()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.image, contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview17() {
    ComposeBookExampleTheme {
        MainScreen9()
    }
}