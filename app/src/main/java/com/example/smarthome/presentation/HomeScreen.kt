package com.example.smarthome.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.smarthome.ui.theme.BackgroundDark
import com.example.smarthome.ui.theme.BackgroundSecondary
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val filteredDevices by remember(
        uiState.devices,
        uiState.searchQuery,
        uiState.selectedFilter
    ) {
        derivedStateOf {
            viewModel.filteredDevices()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundSecondary,
                        BackgroundDark
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 20.dp,
                end = 16.dp,
                bottom = 24.dp
            )
        ) {
            item {
                HeaderSection()
            }

            item {
                DeviceFilterChips(
                    selected = uiState.selectedFilter,
                    onSelected = viewModel::onFilterSelected
                )
            }

            item {
                DeviceSearchBar(
                    query = uiState.searchQuery,
                    onQueryChanged = viewModel::onSearchChanged
                )
            }

            items(
                items = filteredDevices,
                key = { device -> device.id }
            ) { device ->
                ExpandableDeviceCard(
                    device = device,
                    onClick = {
                        viewModel.onDeviceClicked(device)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}