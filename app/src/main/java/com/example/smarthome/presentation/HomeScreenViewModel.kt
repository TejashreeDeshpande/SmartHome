package com.example.smarthome.presentation

import androidx.lifecycle.ViewModel
import com.example.smarthome.data.MockSmartHomeRepository
import com.example.smarthome.domain.DeviceFilter
import com.example.smarthome.domain.DeviceStatus
import com.example.smarthome.domain.DeviceType
import com.example.smarthome.domain.SmartDevice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel(
    val repository: MockSmartHomeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeScreenUiState(devices = repository.getDevices())
    )
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun onSearchChanged(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }
    fun onFilterSelected(filter: DeviceFilter) {
        _uiState.update {
            it.copy(selectedFilter = filter)
        }
    }

    fun onDeviceClicked(device: SmartDevice) {
        _uiState.update {
            it.copy(selectedDevice = device)
        }
    }

    fun filteredDevices(): List<SmartDevice> {
        val state = _uiState.value

        return state.devices.filter { device ->
            val matchesSearch =
                device.name.contains(state.searchQuery, ignoreCase = true) ||
                        device.room.contains(state.searchQuery, ignoreCase = true)


            val matchedFilter  = when (state.selectedFilter) {
                DeviceFilter.Online -> device.status == DeviceStatus.ONLINE
                DeviceFilter.Offline -> device.status == DeviceStatus.OFFLINE
                DeviceFilter.Lights -> device.type == DeviceType.LIGHT
                DeviceFilter.Security -> device.type == DeviceType.CAMERA || device.type == DeviceType.LOCK
                else -> true
            }
            matchesSearch && matchedFilter
        }
    }
}