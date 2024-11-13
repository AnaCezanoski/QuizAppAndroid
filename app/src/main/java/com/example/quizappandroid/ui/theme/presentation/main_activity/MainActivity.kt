package com.example.quizappandroid.ui.theme.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizappandroid.R
import com.example.quizappandroid.ui.theme.presentation.home.HomeScreen
import com.example.quizappandroid.ui.theme.presentation.home.HomeScreenViewModel
import com.example.quizappandroid.ui.theme.presentation.nav_graph.SetNavGraph
import com.example.quizappandroid.ui.theme.ui.QuizAppAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            QuizAppAndroidTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.white)),
                    contentAlignment = Alignment.Center
                ) {
                    SetNavGraph()
                }
            }
        }
    }
}