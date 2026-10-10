package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.app.Activity
import com.example.ui.MatchGrid
import com.example.ui.LinksScreen
import com.example.ui.VideoPlayer
import com.example.ui.DMCADialog
import com.example.ui.PTVHeader
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var showPolicy by remember { mutableStateOf(false) }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val view = LocalView.current

                LaunchedEffect(currentRoute) {
                    val window = (view.context as Activity).window
                    val controller = WindowCompat.getInsetsController(window, view)
                    if (currentRoute?.startsWith("player/") == true) {
                        controller.hide(WindowInsetsCompat.Type.systemBars())
                        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    } else {
                        controller.show(WindowInsetsCompat.Type.systemBars())
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (currentRoute?.startsWith("player/") != true) {
                            PTVHeader(onPolicyClick = { showPolicy = true })
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (showPolicy) {
                            DMCADialog(onDismiss = { showPolicy = false })
                        }
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("home") {
                                MatchGrid(onCategoryClick = { categoryId ->
                                    navController.navigate("category/$categoryId")
                                })
                            }
                            composable("category/{categoryId}") { backStackEntry ->
                                val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
                                LinksScreen(categoryId = categoryId, onLinkClick = { url ->
                                    navController.navigate("player/${java.net.URLEncoder.encode(url, "UTF-8")}")
                                })
                            }
                            composable("player/{url}") { backStackEntry ->
                                val url = java.net.URLDecoder.decode(backStackEntry.arguments?.getString("url") ?: "", "UTF-8")
                                VideoPlayer(url = url, isDesktop = url.contains("ch=1"))
                            }
                        }
                    }
                }
            }
        }
    }
}
