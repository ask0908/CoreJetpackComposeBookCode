package com.example.composebookexample

sealed class BottomNavRoutes(val route: String) {
    object Home: NavRoutes("home")
    object Contacts: NavRoutes("contacts")
    object Favorites: NavRoutes("favorites")
}
