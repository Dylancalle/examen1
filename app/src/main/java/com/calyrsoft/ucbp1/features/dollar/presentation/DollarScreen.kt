package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// --- Funciones de utilidad ---

/**
 * Función para formatear el timestamp a una cadena de fecha/hora legible.
 */
fun formatTimestamp(timestamp: Long): String {
    return SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date(timestamp))
}

/**
 * Componente Composable para mostrar una tasa de cambio de manera destacada. (Mejora de UI)
 */
@Composable
fun DollarCard(title: String, rate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Bs. $rate", // Prefijo "Bs." para claridad
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// --- Pantalla Principal ---

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            is DollarViewModel.DollarUIState.Success -> {
                // Mostrar la fecha de actualización (1 punto)
                val formattedDate = formatTimestamp(stateValue.data.timestamp)
                Text(
                    text = "Última Actualización: $formattedDate",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 24.dp) // Mayor espacio
                )

                // Aplicar la mejora de UI para los 4 campos (5 puntos)
                DollarCard(
                    title = "TASA OFICIAL (BCV)",
                    rate = stateValue.data.dollarOfficial!!
                )
                Spacer(modifier = Modifier.height(10.dp))
                DollarCard(
                    title = "PARALELO (Promedio)",
                    rate = stateValue.data.dollarParallel!!
                )
                Spacer(modifier = Modifier.height(10.dp))
                DollarCard(
                    title = "USDT (Tether)",
                    rate = stateValue.data.dollarUSDT!!
                )
                Spacer(modifier = Modifier.height(10.dp))
                DollarCard(
                    title = "USDC (Coinbase/Circle)",
                    rate = stateValue.data.dollarUSDC!!
                )
            }
        }
    }
}