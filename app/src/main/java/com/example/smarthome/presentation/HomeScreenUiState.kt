package com.example.smarthome.presentation

import com.example.smarthome.domain.DeviceFilter
import com.example.smarthome.domain.SmartDevice

data class HomeScreenUiState(
    val devices: List<SmartDevice> = emptyList(),
    val searchQuery: String = "",
    val selectedFilter: DeviceFilter = DeviceFilter.All,
    val selectedDevice: SmartDevice? = null
)