package com.example.composefragment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composefragment.blue.addBlueScreen
import com.example.composefragment.red.addRedScreen
import com.example.composefragment.ui.theme.ComposeFragmentTheme

class MainActivity : FragmentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val navController = rememberNavController()
            
            ComposeFragmentTheme {
                NavHost(
                    navController = navController,
                    startDestination = "main_fragment",
                ) {

                    composable("main_fragment") {
                        MainFragment(navController)
                    }
//
//                    addStartScreen(navController)
                    addBlueScreen(navController)
//                    addRedScreen(navController)
                }
            }
        }
    }
}
