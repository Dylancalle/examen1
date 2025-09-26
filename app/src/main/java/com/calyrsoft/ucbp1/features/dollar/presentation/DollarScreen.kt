package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                Text("Dólar Oficial: ${stateValue.data.dollarOfficial!!}") // Etiqueta agregada para claridad
                Text("Dólar Paralelo: ${stateValue.data.dollarParallel!!}") // Etiqueta agregada para claridad
                Text("USDT a BS: ${stateValue.data.dollarUSDT!!}") // Campo USDT
                Text("USDC a BS: ${stateValue.data.dollarUSDC!!}") // Campo USDC
            }
        }
    }
}