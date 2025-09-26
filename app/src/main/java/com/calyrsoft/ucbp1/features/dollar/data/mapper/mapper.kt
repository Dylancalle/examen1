package com.calyrsoft.ucbp1.features.dollar.data.mapper

import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel() : DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUSDT = dollarUSDT,
        dollarUSDC = dollarUSDC,
        timestamp = timestamp
    )
}

fun DollarModel.toEntity() : DollarEntity {
    // Al guardar en la base de datos, se usa el timestamp del modelo si existe,
    // o se genera uno nuevo para el registro histórico si es un objeto nuevo.
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUSDT = dollarUSDT,
        dollarUSDC = dollarUSDC,
        timestamp = System.currentTimeMillis()
    )
}