package com.example.android

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android.features.featureone.ui.FeatureOneScreen
import com.example.android.features.featureone.ui.ROUTE_FEATURE_ONE

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        NavHost(
            navController = navController,
            startDestination = ROUTE_FEATURE_ONE,
        ) {
            composable(ROUTE_FEATURE_ONE) {
                FeatureOneScreen(
                    viewModel = hiltViewModel(),
                )
            }
        }
    }
}
