package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    var dollarUSDT: String? = null, // Nuevo campo para el tipo de cambio USDT a BS
    var dollarUSDC: String? = null  // Nuevo campo para el tipo de cambio USDC a BS
)